rediscala client test
======================

This sample code test scala redis client by initiaing load on redis.
The test uses the https://github.com/etaty/rediscala client

### Set up your project dependencies

Run the setup.sh script to install scala and sbt.

### Change the readerEndpoint to your redis reader endpoint FQDN
```scala
var readerEndpoint  = "redis-test-2-ro.8grenv.ng.0001.use1.cache.amazonaws.com"
```



### Run the test

>sbt
sbt:scala-redis>compile
sbt:scala-redis>run
1. clone the repo `git clone git@github.com:zivlaub/scala-redis.git`
2. run 'source $HOME/.sdkman/bin/sdkman-init.sh'
2. run `sbt compile`
3. run `sbt run`


