package HackingScala.snippets.usage

object constructors extends App {
	println(new Person("jason", "lin", 9))
	println(new Person("eric"))
	println(new Person("andrew", "ning"))
	
	class Person(val firstName: String, val lastName: String, val age: Int) {
	  def this(firstName: String) {
	    this(firstName, "", 0)
	    println("No lastname or age given")
	  }
	  
	  def this(firstName: String, lastName: String) {
	    this(firstName, lastName, 0)
	    println("No age given")
	  }
	  
	  override def toString: String = {
	    return "%s %s, aged %d".format(firstName, lastName, age)
	  }
	}
}

