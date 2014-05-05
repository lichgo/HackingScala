package HackingScala.projects.assets

import scala.collection.mutable.Map

object StockPriceSpider extends App {
	def getLatestClosingPrice(symbol: String) = {
		val url = "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&a=00&b=01&c=" + new java.util.Date().getYear

		val data = scala.io.Source.fromURL(url).mkString
		val mostRecentData = data.split("\n")(1)
		val closingPrice = mostRecentData.split(",")(4).toDouble
		closingPrice
	}
	
	println("GOOG: " + getLatestClosingPrice("GOOG"))

	def getStockUnitsMap() = {
		val stocks = scala.xml.XML.load("src/HackingScala/projects/assets/stocks.xml")

		(Map[String, Int]() /: (stocks \ "symbol")) {
			(map, symbolNode) =>
				val ticker = (symbolNode \ "@ticker").toString
				val units = (symbolNode \ "units").text.toInt
				map(ticker) = units // always return a new map
				map	// returned value is the tmp map
		}
	}
}