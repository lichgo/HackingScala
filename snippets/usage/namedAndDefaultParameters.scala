package HackingScala.snippets.usage

object namedAndDefaultParameters extends App {
	def printName(firstName: String = "UNKNOWN", lastName: String = "UNKNOWN") {
	  System.out.format("Your name is %s %s", firstName, lastName);
	}
	
	printName(firstName = "FiratName", lastName = "LastName")
	printName()
}