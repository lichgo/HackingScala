package HackingScala.concurrency

import scala.actors.Actor._

object message extends App {
  var startTime: Long = 0
  val caller = self
  
  val engrossActor = actor {
    println("Number of messages: " + mailboxSize)
    //caller ! "hello1"	// comment this, the main thread will hand there
    //caller ! "hello from engrossActor"
    println("Going to bed now........")
    Thread.sleep(6000)
    println("Number of messages when I was sleeping: " + mailboxSize)
    println("EngrossActor: Waiting for msg.....")
    receive {
      case msg => println("Msg from main: " + msg)
    }
  }
  //engrossActor ! "1 from main"
  println("Main: Waiting for msg.....")
  receive { case msg => println("The message from actors is: " + msg) }
  println("Just received message")
  engrossActor ! "Msg from main thread"
}