package HackingScala.concurrency

import scala.actors.Actor._

object reactMessage extends App {
  def info(msg: String) = println(" {{  INFO: " + msg + " received by " + Thread.currentThread() + "  }} ")
  
  def receiveMessage(id: Int) {
    for (i <- 1 to 2) {
      receiveWithin(20000) {
        case msg: String => info(" { RECEIVE-MESSAGE: " + id + msg + " } ")
      }
    }
  }
  
  def reactMessage(id: Int) {
    react {
      case msg: String => info(" { REACT-MESSAGE: " + id + " " + msg + " } ")
      // Recursive
      reactMessage(id)
    }
  }
  
  println("Current thread: " + Thread.currentThread())
  val actors = Array(
    actor { info("react:   1 actor"); reactMessage(1) },
    actor { info("react:   2 actor"); reactMessage(2) },
    actor { info("receive: 3 actor"); receiveMessage(3) },
    actor { info("receive: 4 actor"); receiveMessage(4) }
  )
  
  Thread.sleep(1000)
  
  // Start to send messages
  for (i <- 0 to 3) {
    actors(i) ! ("Hello " + i)
    Thread.sleep(2000)
  }
  Thread.sleep(2000)
  for (i <- 0 to 3) {
    actors(i) ! ("Hello " + i)
    Thread.sleep(2000)
  }
}