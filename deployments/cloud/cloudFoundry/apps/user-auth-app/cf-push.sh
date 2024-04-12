#--------------------
# Push Applications
# user-auth-app
cf push user-auth-app -f deployments/cloud/cloudFoundry/apps/user-auth-app/user-auth-app.yaml -p applications/user-auth-app/target/user-auth-app-0.0.1-SNAPSHOT.jar