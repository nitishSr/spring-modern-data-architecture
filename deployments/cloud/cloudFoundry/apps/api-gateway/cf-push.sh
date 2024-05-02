#--------------------
# Push Applications
# api-gateway
cf push api-gateway -f deployments/cloud/cloudFoundry/apps/api-gateway/api-gateway.yaml -p applications/api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar