package HackingScala.concurrency

import scala.actors._
import Actor._

object loopwhile extends App {
	val caller = self
	
	val accumulator = actor {
	  var continue = true
	  var sum = 0
	  
	  loopWhile (continue) {
	    reactWithin(500) {
	      case number: Int => sum += number
	      case TIMEOUT =>
	        continue = false
	        caller ! sum
	    }
	  }
	}
	
	accumulator ! 1
	accumulator ! 5
	accumulator ! 4
	
	receiveWithin(1000) {
	  case result => println(result)
	}
}