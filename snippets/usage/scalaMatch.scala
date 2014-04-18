package HackingScala.snippets.usage

object scalaMatch extends App {
	val i = 5;
	
	i match {
	  case 1 => println("Jan")
	  case 2 => println("Feb")
	  // catch the value of i as "whoa"
	  case whoa => println("Unexpected case: " + whoa toString)
	}
	
	// More functional approach (Tableswitch here)
	val month = i match {
	  case 1 => "Jan"
	  case 2 => "Feb"
	  case 3 => "Mar"
	  case _ => "Invalid"
	}
	
	// Match types
	class Person {}
	def getClassAsString(x: Any): String = x match {
	  case s: String => s + " is a string"
	  case i: Int => "Int"
	  case f: Float => "Float"
	  case l: List[_] => "List"
	  case p: Person => "Person"
	  case _ => "Unown"
	}
	
	println(getClassAsString(34))
	println(new Person)
	
	// Better to use a Map instead of Match/Switch
	val monthNumber2Name = Map(
		1 -> "Jan",
		2 -> "Feb",
		3 -> "Mar"
	)
}