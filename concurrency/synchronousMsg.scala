package HackingScala.concurrency

import scala.actors._
import Actor._	// twice import

object synchronousMsg extends App {
  val fortuneTeller = actor {
    for (i <- 1 to 4) {
      Thread.sleep(1000)
      receive {
        //case msg => sender ! (i + msg.toString)	// return msg to the sender
        // donothing
        case msg => 
      }
    }
  }
  
  println( fortuneTeller !? (2000, "first") )	// Some(1first)
  println("do something here......")
  println( fortuneTeller !? (324, "second") )	// None
}