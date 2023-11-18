import redis.RedisClient
import scala.concurrent.{Future, Await}

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, Scala!")

    implicit val akkaSystem = akka.actor.ActorSystem()
    val host = "redis-test-2.8grenv.ng.0001.use1.cache.amazonaws.com"
    val redis = RedisClient(host)
    val s = redis.set("my_key", 1)
    Await.result(s, 5 seconds)
    // val futurePong = redis.ping()
    // println("Ping sent!")
    // futurePong.map(pong => {
    //     println(s"Redis replied with a $pong")
    // })
    // Await.result(futurePong, 5 seconds)

    // val futureResult = read_loop(redis)
    
    // Await.result(futureResult, 5 seconds)
 
    // Create a sequence of tasks to be executed in parallel
    //val tasks: Seq[Future[Unit]] = (1 to 5).map(task)
    var PARALLEL = 5
    val tasks: Seq[Future[Unit]] = (1 to PARALLEL).map(task(_, redis))
    // Use Future.sequence to execute tasks in parallel
    val allTasks: Future[Seq[Unit]] = Future.sequence(tasks)
  
    // Use Await.result to block and wait for all tasks to complete
    // Adjust the timeout according to the expected completion time of tasks
    val result: Seq[Unit] = Await.result(allTasks, 10.seconds)
  
    // Additional logic after all tasks have completed
    println("All tasks completed.")
    
    akkaSystem.terminate()
  }
  
  def doSomething(redis: RedisClient): Future[Boolean] = {
    // launch command set and del in parallel
    val s = redis.set("redis", "is awesome")
    val d = redis.del("i")
    for {
      set <- s
      del <- d
      incr <- redis.incr("i")
      iBefore <- redis.get("i")
      incrBy20 <- redis.incrby("i", 20)
      iAfter <- redis.get("i")
    } yield {
      println("SET redis \"is awesome\"")
      println("DEL i")
      println("INCR i")
      println("INCRBY i 20")
      val ibefore = iBefore.map(_.utf8String)
      val iafter = iAfter.map(_.utf8String)
      println(s"i was $ibefore, now is $iafter")
      iafter == "20"
    }
  }
  
  def read_loop( redis: RedisClient) : Future[Boolean] = {
    
    for {
      //set <- s
      iBefore <- redis.get("my_key")
    } yield {
      val ibefore = iBefore.map(_.utf8String)
      println(s"my_key is $ibefore")
      ibefore == "1"
    }
  }
    
  def task(taskId: Int, redis: RedisClient): Future[Unit] = Future {
    
    //val result = redis.ping()
    val futureResult = read_loop(redis)
    Await.result(futureResult, 5 seconds)
    //println(s"Task $taskId completed. $futureResult")
  }
  
  
  
  
}
