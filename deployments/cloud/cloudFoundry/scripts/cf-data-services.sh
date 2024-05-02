#
# /*
#  * Copyright 2023 VMware, Inc.
#  * SPDX-License-Identifier: GPL-3.0
#  */
#



# List Services

## GemFire for Redis

cf create-service p-cloudcache extra-small  retail-gf-redis -c '{"gemfire_for_redis_enabled":"true","gemfire_for_redis_redundant_copies":1,"gemfire_for_redis_region_name": "GF_REDIS"}' -t redis

## SQL

#cf create-service p.mysql free retail-sql
cf create-service postgres  on-demand-postgres-db retail-sql


## RabbitMQ

cf create-service p.rabbitmq on-demand-plan retail-rabbitmq

#cf update-service retail-rabbitmq -c '{ "plugins": { "rabbitmq_stream": true, "rabbitmq_stream_management": true } }'

# -----------------------------
# WAIT FOR SERVICE to be available

rabbit_status=`cf service retail-rabbitmq | grep status:`

while [[ "$rabbit_status" != *"create succeeded"* ]]
do
  echo "Waiting for Rabbitmq, current status:" $rabbit_status
  sleep 1
  rabbit_status=`cf service retail-rabbitmq | grep status:`
done


mysql_status=`cf service retail-sql | grep status:`
echo "Waiting for mysql, current status:" $mysql_status
while [[ "$mysql_status" != *"create succeeded"* ]]
do
  echo "Waiting for mysql, current status:" $mysql_status
  sleep 1
  mysql_status=`cf service retail-sql | grep status:`
done


gemfire_status=`cf service retail-gf-redis | grep status:`
echo "Waiting for gemfire, current status:" $gemfire_status
while [[ "$gemfire_status" != *"create succeeded"* ]]
do
  echo "Waiting for gemfire, current status:" $gemfire_status
  gemfire_status=`cf service retail-gf-redis | grep status:`
  sleep 1
done

#-------------------
# Create a service key GemFire
cf create-service-key retail-gf-redis retail-gf-redis-key

# Inspect the service key:
cf service-key retail-gf-redis retail-gf-redis-key

#-------------------
# Create a service key RabbitMQ
cf create-service-key retail-rabbitmq retail-rabbitmq-key -c '{"tags":"administrator"}'

# Inspect the service key:
cf service-key retail-rabbitmq retail-rabbitmq-key


#-------------------
# Create a service key MySQL
cf create-service-key retail-sql retail-sql-key

# Inspect the service key:
cf service-key retail-sql retail-sql-key
