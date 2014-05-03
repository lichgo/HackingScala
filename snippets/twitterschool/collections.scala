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
  // element of a map is a tuple
  val nameMap = Map( "jason" -> 25, "duanduan" -> 23 )
  val people = nameMap.filter( (nameage: (String, Int)) => nameage._2 > 24 )
  println("people: " + people)
  
  // zip
  val zipList = List(1, 2, 3) zip List('a', 'b', 'c', 'd')
  println(zipList)		// List((1,a), (2,b), (3,c)), d is ignored
  
  // partition
  println(List(1,2,3,4,5,6,7,8) partition(_ % 2 == 1))	// (List(1, 3, 5, 7),List(2, 4, 6, 8)), return a tuple
  
  // find
  println(List(1,2,3,4).find(i => i > 2))	// Some(3)	only the first one
  
  // drop
  println(list.drop(3))	//List(4)
  println(list.dropWhile(_ % 2 == 1))	//List(2,3,4)
  
  // foldLeft (foldRight)
  val sum = list.foldLeft(100)( (m, n) => m + n ) // the first is element value, the second is tmp result
  println("sum " + sum)
  
  // flatten
  println( List(List(1, 2), List('a', 'b', "easy")) flatten )// List(1,2,a,b,easy) list allows multiple types in a list
  
  // flatmap
  println( List( List(1,2), List(3,4) ).flatMap( x => x.map(_ + 2) ) )
}