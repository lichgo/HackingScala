import scala.actors.Actor._

object receiveWithin extends App {
	val caller = self

	var accumulator = actor {
		var sum = 0
		var continue = true
		while (continue) {
			sum += receiveWithin(1000) {
				case number: Int => number
				case TIMEOUT =>
					println("Time out now")
					continue = false
					0
			}
		}
		caller ! sum
	}

	accumulator ! 1
	accumulator ! 2
	accumulator ! 3

	receiveWithin(1000) {
		case result => println("Total is: " + result)
		case _ => println("Handling other msg pattern")
	}
}