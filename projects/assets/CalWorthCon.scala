package HackingScala.projects.assets

import scala.actors._
import Actor._

object CalWorthCon extends App {
	val stockUnitsMap = StockPriceSpider getStockUnitsMap

	val caller = self

	println("Today is " + new java.util.Date())
	println("Ticker Units Closing Price($) Total Value($)")

	val startTime = System.nanoTime()

	stockUnitsMap.keys.foreach(
		symbol => actor { caller ! (symbol, StockPriceSpider getLatestClosingPrice symbol) }
	)

	val netWorth = (0.0 /: (1 to stockUnitsMap.size)) {
		(worth, index) =>
			receiveWithin(10000) {
				case (symbol: String, latestClosingPrice: Double) =>
					val units = stockUnitsMap(symbol)
					val value = units * latestClosingPrice
					println("%-7s %-5d %-16f %f".format(symbol, units, latestClosingPrice, value))
					worth + value
				case TIMEOUT => 
					println("Timeout")
					0
			}
	}

	val endTime = System.nanoTime()

	println("The total value of your investment is $" + netWorth)	//233857
	println("(Took " + (endTime - startTime) / 1000000000 + " seconds)")	//12s
}