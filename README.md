rediscala client test
======================

This sample code test redis client.

### Set up your project dependencies

Run the setup.sh script to install scala and sbt.

### Change the readerEndpoint with your redis reader endpoint FQDN

var readerEndpoint  = "redis-test-2-ro.8grenv.ng.0001.use1.cache.amazonaws.com"
   

### Run the test

>sbt
sbt:scala-redis>compile
sbt:scala-redis>run


