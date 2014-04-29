package HackingScala.concurrency

import scala.actors._
import Actor._

object actorTrait extends App {
	
  class AnsweringService(val folks: String*) extends Actor {
    def act() {
      while (true) {
        receive {
          // match msg invocation format from line 26 (a tuple)
          case (caller: Actor, name: String, msg: String) =>
            caller ! (
              // *.contains(value)
              if (folks.contains(name)) String.format("%s: %s", name, msg)
              else String.format("There is no name(%s) here", name)
            )
          case "ping" => println("ping!")
          case "quit" => println("exiting actor")
            exit
        }
      }
    }
  }
  
  val service1 = new AnsweringService("jason", "duanduan")
  service1 ! (self, "jason", "in BJ")
  service1 ! (self, "duanduan", "in HK")
  
  service1.start()

  service1 ! (self, "eric", "in ST")
  service1 ! (self, "andrew", "in SF")
  
  for (i <- 1 to 3) {	// receive 3 times, so andrew msg will not be "received"
    println("RECEIVING.....")
    receive {
      case msg => println(msg)
    }
  }

  service1 ! "ping"
  service1 ! "quit"
  service1 ! "ping"

  Thread.sleep(2000)
  println("The last ping was not processed.")
}