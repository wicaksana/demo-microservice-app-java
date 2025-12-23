#/bin/sh
set +x

APP_URL="https://demo-app.wicaksana.uk/failover/api/v1/order"
echo $APP_URL
for i in `seq 1 60`; 
    do echo -n "$i. " \
    && curl -isk "$APP_URL" \
    --header 'Content-Type: application/json' \
    --data '{"productId":1,"quantity": 2}' \
    | grep 'x-api-version'; done
    sleep 1

