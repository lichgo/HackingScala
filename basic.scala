object Basic {
  def main(args: Array[String]) {
	val msg = "Hello, Scala"
	println(msg)
	
	// function
	def max(x: Int, y: Int): Int = {
		if (x > y) x
		else y
	}
	
	def max2(x: Int, y: Int) = if (x > y) x else y
	
	max(3, 5)
	max2(4, 5)
	
	def greeting() = println("Hello")
	
	// foreach & for
	args.foreach(arg => println(arg))
	args.foreach(print)	// single parameter
	
	for (arg <- args)
		println(arg)
	
	// Parameterize
	val msgs = new Array[String](3)
	msgs(0) = "A"
	msgs.update(0, "a")
	msgs(1) = "B"
	msgs(2) = "C"
	for (i <- 0 to 2) {
		print(msgs(i))
		print(msgs.apply(i))
	}
	
	// All operatons are member functions
	(1).+(2)
	val nums = Array("zero", "one", "two")
	
	// List: immutable
	val oneTwoThree = List(1, 2, 3)
	val oneTwo = List(1, 2)
	val threeFour = List(3, 4)
	val oneTwoThreeFour = oneTwo ::: threeFour 	//1,2,3,4
	
	val _123 = 1 :: threeFour 	//1,2,3
	
	val _1234 = 1 :: 2 :: 3 :: 4 :: Nil
	
	// Tuple
	val pair = (99, "scala")
	pair._1
	pair._2
	
	// Set: immutable by default.
	var jetSet = Set("a", "b")	// immutable -> point to another -> var
	jetSet += "c"	// jetSet == (a, b, c), a new instance of set
	jetSet.contains("d")
	
	import scala.collection.mutable.Set
	val movieSet = Set("duanduan", "canhong")	// Mutable, can change, so val always points to the original one
	movieSet += "love"
	println(movieSet)
	
	// Map 
	import scala.collection.mutable.Map
	val treasureMap = Map[Int, String]()
	treasureMap += (1 -> "One")
	treasureMap += (2 -> "Two")
	treasureMap(2)
	// (immutable by default if not importing the class)
	val romanNum = Map(
			1 -> "one", 2 -> "two"
		)
	
	// File IO
	import scala.io.Source
	if (args.length > 0) {
		for (line <- Source.fromFile(args(3)).getLines)
			print(line.length + " " + line)
	} else
		Console.err.println("File error")

  }
}