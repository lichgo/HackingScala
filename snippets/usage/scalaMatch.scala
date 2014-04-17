package HackingScala.snippets.usage

object scalaMatch extends App {
	val i = 5;
	
	i match {
	  case 1 => println("Jan")
	  case 2 => println("Feb")
	  // catch the value of i as "whoa"
	  case whoa => println("Unexpected case: " + whoa toString)
	}
	
	// More functional approach
	val month = i match {
	  case 1 => "Jan"
	  case 2 => "Feb"
	  case 3 => "Mar"
	  case _ => "Invalid"
	}
}