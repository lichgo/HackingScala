package HackingScala.snippets.twitterschool

object collections extends App {
  
  // ----------------- Collection ----------------- 
  // List
  val list = List(1, 2, 3, 4)
  
  // Set
  val set = Set(1, 1, 2)
  
  // Tuple
  val tuple = ("localhost", 80)
  println(tuple._1 + ":" + tuple._2)
  val result = tuple match {
    case ("localhost", 80) => "Correct!"
    case ("localhost", 8080) => "Nope :("
  }
  println(result)
  
  // Map
  val map = Map(1 -> Map("foo" -> "bar"), "set" -> { set }, 3 -> "hi ")
  
  // Option
  /*
   * Trait Option[T] {
   * 	def isDefined: Boolean
   *  	def get: T					// May not return any type of result
   *    def getOrElse(t: T): T
   * }
   * subclass: Some[T], None[T]
   */
  println(map.get(1))	// Some(Map(foo -> bar))
  println(map.get(2))	// None
  
  // getOrElse / match
  println((map.get(3)).getOrElse(0) + " done")
  
  val res = (map.get(4)) match {
    case Some(n) => n + " Some"
    case None => 0
  }
  println(res)			// 
  
  
  // ----------------- Function Combinators ----------------- 
  // map: pass a function (functon literal, partial function), return a sequence
  println(list.map( i => i * 2 ))
  def addTen(i: Int) = i + 10
  println(list.map( addTen _ ))
  
  // foreach: same with map, but return void
  
  // filter: pass a function, returns a sequence
  println(list.filter( i => i % 2 == 0 ))
  def isEven(i: Int) = i % 2 == 0
  println(list.filter( isEven _ ))
}