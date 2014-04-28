import scala.actors.Actor._

object actor extends App {
	
	def isPrime(number: Int) = {
		var result = true

		if (number == 2 || number == 3) result = true

		for (i <- 2 to Math.sqrt(number.toDouble).floor.toInt; if result) {
			if (number % i == 0) result = false
		}

		result
	}

	// Use a thread to evaluate if a number if prime
	val primeActor = actor {
		// Use a var to control loop, the var is reset in some MSG case in receive{}
		var continue = true

		while (continue) {
			receive {
				case (caller: Actor, number: Char) => caller ! (number, isPrime(number))
				// Just wrap the method inside a " actor{ } "
				case (caller: Actor, number: Int) => actor { caller ! (number, isPrime(number)) }
				case "quit" => continue = false
			}
		}
	}

	// Send msg to the same act => run sequentially
	primeActor ! (self, 2)
	primeActor ! (self, 131)
	primeActor ! (self, 132)

	for (i <- 1 to 3) {
		receive {
			case (number, result) => println(number + ": " + result)
		}
	}

	primeActor ! "quit"
}