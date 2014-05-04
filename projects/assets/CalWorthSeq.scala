package HackingScala.projects.assets

object CalWorthSeq {
	val stockUnitsMap = StockPriceSpider getStockUnitsMap

	println("Today is " + new ajava.util.Date())
	println("Ticker\t\tUnits\t\tClosing Price($)\t\tTotal Value($)")

	val startTime = System.nanoTime()

	val netWorth = (0.0 /: stockUnitsMap) {
		(worth, element) =>
			val (symbol, units) = element

			val latestClosingPrice = StockPriceSpider getLatestClosingPrice symbol
			val val = latestClosingPrice * units

			worth += val

			println("%-7s %-5d %-16f %f".format(symbol, units, latestClosingPrice, val))
	}

	val endTime = System.nanoTime()

	println("The total value of your investments is $" + netWorth)
	println("(Took " + (endTime - startTime) / 1000000000 + " seconds)")
}