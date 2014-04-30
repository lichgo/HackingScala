package HackingScala.concurrency

import scala.actors._
import Actor._

object reactWithTimeout extends App {
  val caller = self
  
  def accumulate(sum: Int) {
    reactWithin(500) {
      case number: Int => accumulate(sum + number)
      case TIMEOUT => caller ! sum
    }
    println("This line will never be called....")
  }
  
  val accumulator = actor { accumulate(0) }
  accumulator ! 1
  accumulator ! 7
  accumulator ! 8
  
  receiveWithin(100000) {
    case result => println("Result: " + result)
  }
  println("done")
}