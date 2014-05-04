package HackingScala.projects.assets

object CalWorthSeq extends App {
	val stockUnitsMap = StockPriceSpider getStockUnitsMap

	println("Today is " + new java.util.Date())
	println("Ticker Units Closing Price($) Total Value($)")

	val startTime = System.nanoTime()

	val netWorth = (0.0 /: stockUnitsMap) {
		(worth, element) =>
			val (symbol, units) = element

			val latestClosingPrice = StockPriceSpider getLatestClosingPrice symbol
			val value =  units * latestClosingPrice
			
			println("%-7s %-5d %-16f %f".format(symbol, units, latestClosingPrice, value))

			worth + value
	}

	val endTime = System.nanoTime()

	println("The total value of your investments is $" + netWorth)	//233857
	println("(Took " + (endTime - startTime) / 1000000000 + " seconds)")	//67s
}