package HackingScala.snippets.twitterschool

object global extends App {
  // ----------------------  FUNCTIONS ---------------------- 
  // if no args, brackets can be removed
  def three()  = 1 + 2
  println(three())
  println(three)
  
  // anonymous function
  (x: Int) => x + 1
  val addOne = (x: Int) => x + 1
  println(addOne(1))
  // using curly brackets
//  {
//    i: Int =>
//      println("hello")
//      i * 2
//  }
  
  // partial application
  def adder(m: Int, n: Int) = m + n
  val add2 = adder(2, _: Int)
  println(add2(4))
  
  // curry: a function with over two brackets
  def multiple(m: Int)(n: Int) = m * n
  val timesTwo = multiple(2) _
  println(timesTwo(4))
  // can currize any function with multiple parameters
  val curried = (adder _).curried
  val addsTwo = curried(2)
  println(addsTwo(5))
  
  // multiple arguments with *
  def capitalizeAll(args: String*) = {
    println(args.getClass())	// class scala.collection.mutable.WrappedArray$ofRef
    args.map { arg => arg.capitalize }
  }
  val capArgs = capitalizeAll("jason", "lin")
  println(capArgs)
  println(capArgs.getClass())	// class scala.collection.mutable.ArrayBuffer
  
  
  // ------------------- Class --------------------
  class Calculator(brandName: String) {
    // --- Constructor starts ---
    val color: String =
      if (brand == "TI") "blue"
      else if (brand == "HP") "black"
      else "white"
    // --- Constructor ends -----
    val brand: String = "HP"
    def add(m: Int, n: Int): Int = m + n
    def getColor = color	// no arg, no (); simple expression, use only =
    val func = () => color + " haha"	// cal.func is a function
  }
  
  val cal = new Calculator("li")
  println(cal.add(1, 2) + cal.brand + cal.color + cal.func())
  println(cal.getColor)
  
  // abstract class
  abstract class Shape {
    def getArea(): Int	// subclass should define this
    def getId(): String = "id"	// abstract class can have concrete methods
  }
  class Circle(r: Int) extends Shape {
    def getArea(): Int = r * r * 3
  }
  val circle = new Circle(4)
  println(circle getArea)
  
  
  // ---------------- Traits ----------------
  trait Car {
    val brand: String
  }
  trait Shiny {
    val shineRefraction: Int
  }
  class BMW extends Car with Shiny {
    val brand = "BMW"
    val shineRefraction = 12
  }
  
  
  // ----------------- Generic type of arg --------------------
  trait Cache[K, V] {
    def get(key: K): V
    def put(key: K, value: V)
    def delete(key: K)
  }
  def remove[K](key: K) {  }
  
  
  // ------------------- Using apply() ---------------------
  // in object
  class Foo {}
  object FooFactory {
    def apply() = new Foo	// () can be removed
  }
  println(FooFactory())	// foofactory like a method
  
  // in class
  class Bar {
    def apply() = 0
  }
  println((new Bar)())	// 0
  
  // All functions are objects
  object addTen extends Function1[Int, Int] { // extend (Int => Int)
    def apply(m: Int): Int = m + 10
  }
  println(addTen(90))
  
  class AddThousand extends (Int => Int) {
    def apply(m: Int): Int = m + 1000
  }
  val at = new AddThousand	// () is optional
  println(at(9000))
  
  
  // ------------------- Singleton object --------------------
  object Timer {
    var count = 0
    
    def currentCount: Int = {	// () is negletable
      count += 1
      count
    }
  }
  println(Timer.currentCount)
  println(Timer.currentCount)	// If there is no arg, () is neglectable
  
  
  // --------------------- Companion object --------------------
  class Person(foo: String)
  
  object Person {
    def apply(foo: String) = new Person(foo)
  }
  
  val bar = Person("jason")	// 'new' is optional
  
  
  // --------------------- Match ------------------------
  val times = 1
  
  // match value
  times match {
    case 1 => "one"
    case 2 => "two"
    case _ => "other"
  }
  
  times match {
    case i if i == 1 => "one"
    case i if i == 2 => "two"
    case _ => "other"
  }
  
  // match type
  def bigger(o: Any): Any = {
    o match {
      case i: Int if i < 0 => i - 1
      case i: Int => i + 1
      case d: Double => "Double"
      case text: String => "String"
    }
  }
  // use this in exception handling
  val result: Any = try {
    bigger(12)
  } catch {
    case e: NullPointerException => {
      println("server error")
      "returning errors"
    }
  }
  
  // case classes
  case class Customer(firstName: String, lastName: String)
  val c1 = Customer("jason", "lin")	// new is optional
  val c2 = Customer("jason", "lin")
  println(c1)	// Customer(jason, lin)
  println(c1 == c2)	// True
  
  def customerType(customer: Customer) = customer match {
    case Customer("jason", "lin") => "IT"
    case Customer("duanduan", "chen") => "Finance"
    case Customer(otherFirstName, otherLastName) => "Other: %s %s".format(otherFirstName, otherLastName)	// Customer(_, _)
    case _ => "Other than customer"
  }
  
}