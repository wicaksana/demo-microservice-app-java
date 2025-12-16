# Order service

## Docker

```bash
docker build -t muarwi/order-service .

docker run --rm -it -p 8080:8080 muarwi/order-service

# OR:
docker run --rm -it \
  -p 8080:8080 \
  --add-host host.docker.internal:host-gateway \
  -e SPRING_HTTP_CLIENT_SERVICE_GROUP_INVENTORY_BASE_URL=http://host.docker.internal:8081 \
  muarwi/order-service

```
spring.http.client.service.group.inventory.base-url