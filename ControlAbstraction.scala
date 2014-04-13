import java.io.PrintWriter
import java.io.File

object Abstract {
	object FileMatcher {
		private def filesHere = (new java.io.File(".")).listFiles
	
		// higher-order function (use function as args)
		def filesMatching(query: String, matcher: (String, String) => Boolean) = {
			for (file <- filesHere; if matcher(file.getName, query))
				yield file
		}
	
/*		def filesEnding(query: String) = 
			filesMatching(_.endsWith(query))
		def filesContaining(query: String) = 
			filesMatching(_.contains(query))
		def filesRegex(query: String) =
			filesMatching(_.matches(query))*/
	}
	
	// Simplified client code
	val nums = List(1,2,-4,4)
	def containsNeg(nums: List[Int]) = nums.exists(_ < 0)
	def containsOdd(nums: List[Int]) = nums.exists(_ % 2 == 1)
	
	// Curry (returning function)
	def plainOldSum(x: Int, y: Int) = x + y
	def curriedSum(x: Int)(y: Int) = x + y 	//curriedSum(1)(2)
	def onePlus = curriedSum(1)_
	def twoPlus = curriedSum(2)_
	
	def twice(op: Double => Double, x: Double) = op(op(x))
	twice(_ + 1, 5)	//7
	
	
	// loan pattern
	def withPrintWriter(file: File, op: PrintWriter => Unit) {
		val writer = new PrintWriter(file)
		try {
			op(writer)
		} finally {
			writer.close()
		}
	}
	withPrintWriter(
		new File("date.txt"),
		writer => writer.println(new java.util.Date)
	)
	
	def withPrintWriter(file: File)(op: PrintWriter => Unit) {
		val writer = new PrintWriter(file)
		try {
			op(writer)
		} finally {
			writer.close()
		}
	}
	val file = new File("date.txt")
	withPrintWriter(file) {
		writer => writer.println(new java.util.Date)
	}

}