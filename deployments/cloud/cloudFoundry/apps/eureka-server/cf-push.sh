#--------------------
# Push Applications
# eureka-server
cf push eureka-server -f deployments/cloud/cloudFoundry/apps/eureka-server/eureka-server.yaml -p applications/eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar