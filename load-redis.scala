import redis.RedisClient
import scala.concurrent.{Future, Await}

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, Scala!")

    implicit val akkaSystem = akka.actor.ActorSystem()
    val primaryEndpoint = "redis-test-2.8grenv.ng.0001.use1.cache.amazonaws.com"
    var readerEndpoint  = "redis-test-2-ro.8grenv.ng.0001.use1.cache.amazonaws.com"
    val redis = RedisClient(readerEndpoint)
   
    var ITERRATIONS = 10000
    
    var PARALLEL = 50
   
    for (i <- 1 to ITERRATIONS) {
        val tasks: Seq[Future[Unit]] = (1 to PARALLEL).map(task(_, redis))
        val allTasks: Future[Seq[Unit]] = Future.sequence(tasks)
        val result: Seq[Unit] = Await.result(allTasks, 60.seconds)
        Thread.sleep(50)
        println(s"Batch: $i")
    }
   
    println("All tasks completed.")
    
    akkaSystem.terminate()
  }
  
 
  def readKey( redis: RedisClient) : Future[Boolean] = {
    for {
      iBefore <- redis.get("my_key")
    } yield {
      val ibefore = iBefore.map(_.utf8String)
      ibefore == "1"
    }
  }
    
  def task(taskId: Int, redis: RedisClient): Future[Unit] = Future {
    val futureResult = readKey(redis)
    Await.result(futureResult, 60 seconds)
  }
  
  
  
  
}
