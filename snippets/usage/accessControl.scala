object accessControl extends App {
	
  class Customer(private var age: Int, val id: Int) {
    println("Initiating a customer...")
    def this(age: Int) = {
      this(age, -1)
      println("using short constructor")
    }
  }
  
  val eric = new Customer(23, 1123)
  println(eric.id)
  // println(eric.age)		// compile error, private
  val jason = new Customer(25)
}