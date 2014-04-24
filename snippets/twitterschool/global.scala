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
}