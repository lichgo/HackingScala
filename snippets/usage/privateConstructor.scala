package HackingScala.snippets.usage

object privateConstructor extends App {
	class Order private()
	
	class Customer private(customerId: Long) {
	  def this(id: Long, name: String) {
	    this(id);
	  }
	}
}