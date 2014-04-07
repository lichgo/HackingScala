// local functions
def processFile(filename: String, width: Int) {
	// local function
	def processLine(filename: String, width: Int, line: String) {
		if (line.length > width) print(filename + ": " + line)
	}
	// using closure
	def processLine(line: String) {
		if (line.length > width)
			print(filename + ": " + line)
	}

	var source = Source.fromFile(filename)
	for (line <- source.getLines) {
		processLine(filename, width, line)
	}
}

// function literal
(x: Int) => x + 1
// function value
var increase = (x: Int) => x + 1

// using function as parameter
val nums = List(-1, -2, 11, 2, 5)
nums.foreach((x: Int) => println(x))
// using filter in collection(list)
nums.filter((x: Int) => x > 0) // return a list
// Further simplized
nums.filter(x => x > 0)
nums.filter(_ > 0)
nums.foreach(println _)
// More simple if only one parameter
nums.foreach(println)

// occupying symbol
val f = (_: Int) + (_: Int)

// Partially applied function
def sum(a: Int, b: Int, c: Int) = a + b + c
val a = sum _
a(1, 2, 3) // a.apply(1, 2, 3) > sum(1,2,3)
val b = sum(1, _: Int, 3)
b(3)




// closure
var more = 1
val addMore = (x: Int) => x + more
addMore(10)	//11
nums.foreach(sum += _)	//accum add




