package HackingScala.concurrency

import scala.actors.Actor._

object reactMessage extends App {
  def info(msg: String) = println(msg + " received by " + Thread.currentThread())
  
  def receiveMessage(id: Int) {
    for (i <- 1 to 2) {
      receiveWithin(20000) {
        case msg: String => info("RECEIVE: " + id + msg)
      }
    }
  }
  
  def reactMessage(id: Int) {
    react {
      case msg: String => info("REACT: " + id + msg)
      // Recursive
      reactMessage(id)
    }
  }
  
  val actors = Array(
    actor { info("react:   1 actor"); reactMessage(1) },
    actor { info("react:   2 actor"); reactMessage(2) },
    actor { info("receive: 3 actor"); receiveMessage(3) },
    actor { info("receive: 4 actor"); receiveMessage(4) }
  )
  
  
}