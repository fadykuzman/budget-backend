#! /usr/bin/env bash
PORT=8180
REALM=master


TOKEN=$(curl -s -X POST \
  http://localhost:$PORT/realms/{$REALM}/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=admin-cli" \
  -d "username=admin" \
  -d "password=admin" | jq -r '.access_token')


# List clients and filter by client ID
CLIENT_RESPONSE=$(curl -s -X GET \
  "http://localhost:$PORT/admin/realms/$REALM/clients?clientId=budget-backend" \
  -H "Authorization: Bearer $TOKEN")

CLIENT_ID=$(echo $CLIENT_RESPONSE | jq -r '.[0].id')

curl -X GET \
  "http://localhost:$PORT/admin/realms/$REALM/clients/$CLIENT_ID/client-secret" \
  -H "Authorization: Bearer $TOKEN"
