# Canary deployment

Assuming Consul dataplane is deployed in `consul` namespace. Consul servers are running on external VMs.

## Deploy the services

```bash
oc create ns order
oc create ns inventory

oc apply -f consul-cni.nad.yaml -n order
oc apply -f consul-cni.nad.yaml -n inventory

oc label namespace order tproxy=true
oc label namespace inventory tproxy=true

oc apply -f inventory.svc.yaml

oc apply -f order.svc.yaml

# order v1
oc apply -f order.deployment1.yaml
# order v2
oc apply -f order.deployment2.yaml

oc apply -f inventory.intention.yaml
```

## Exposing the order service to the world (without TLS)

```bash
# Create API GW pod, service account, and a service in `consul` namespace.
oc apply -f ./apigw/consul.gateway.yaml
oc wait --for=condition=accepted gateway/api-gw-demo-app -n consul --timeout=90s

# Create RBAC roles to allow Consul API GW to interact with Consul servers.
oc apply -f ./apigw/consul.rbac_apigw.yaml

# Create HTTPRoute resource
oc apply -f ./order.httproute.yaml

# Create ReferenceGrant resource to allow the API Gw to access resources in another namespace
oc apply -f order.rg.yaml

# Check the HTTPRoute status. The expected message (Status.Parents.Conditions.Message) is "resolved backend references"
oc describe httproute/httproute-order-service -n consul

# Create service intention for API GW --> Order service
oc apply -f order.intention.yaml 

# Create Openshift's route resource.
# Make sure the `spec.host` matches with the cluster's DNS domain name.
oc apply -f ./apigw/consul.route.yaml
```

## Enable traffic splitting

### Without traffic splitting (default to v1)

```bash
oc apply -f order.resolver.yaml
```

Run the test script (update the domain name in the script accordingly):

```bash
./test_canary.sh

http://demo-app.wicaksana.uk/canary2/api/v1/order
1. x-api-version: v1
2. x-api-version: v1
3. x-api-version: v1
4. x-api-version: v1
5. x-api-version: v1
6. x-api-version: v1
7. x-api-version: v1
8. x-api-version: v1
9. x-api-version: v1
10. x-api-version: v1
```

### 50:50 split between v1 and v2

```bash
oc apply -f order.resolver.yaml
oc apply -f order.splitter50_50.yaml
```
Run the test script (update the domain name in the script accordingly):

```bash
./test_canary.sh

http://demo-app.wicaksana.uk/canary2/api/v1/order
1. x-api-version: v1
2. x-api-version: v2
3. x-api-version: v1
4. x-api-version: v1
5. x-api-version: v2
6. x-api-version: v2
7. x-api-version: v2
8. x-api-version: v2
9. x-api-version: v2
10. x-api-version: v1
```

### Redirect all traffic to v2

```bash
oc apply -f order.resolver.yaml
oc apply -f order.splitter_v2_only.yaml
```
Run the test script (update the domain name in the script accordingly):

```bash
./test_canary.sh
http://demo-app.wicaksana.uk/canary2/api/v1/order
1. x-api-version: v2
2. x-api-version: v2
3. x-api-version: v2
4. x-api-version: v2
5. x-api-version: v2
6. x-api-version: v2
7. x-api-version: v2
8. x-api-version: v2
9. x-api-version: v2
10. x-api-version: v2
```