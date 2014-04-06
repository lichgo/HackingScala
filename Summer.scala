import CheckSumAccumulator.calculate

object Summer {		// extends Application
	def main(args: Array[String]) {
		for (arg <- args)
			println(arg + ": " + calculate(arg))
	}
}