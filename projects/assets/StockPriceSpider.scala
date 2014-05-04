package HackingScala.projects/assets

object StockPriceSpider {
	def getLatestClosingPrice(symbol: String) = {
		val url = "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&a=00&b=01&c=" + new java.util.Date().getYear

		val data = scala.io.Source.fromURL(url).mkString
		val mostRecentData = data.split("\n")(1)
		val closingPrice = mostRecentData.split(",")(4).toDouble
		closingPrice
	}

	def getStockUnitsMap() = {
		val stocks = scala.xml.XML.load("stocks.xml")

		(Map[String, Int]() /: (stocks \ "symbol")) {
			(map, symbolNode) =>
				val ticker = (symbol \ "@ticker").toString
				val units = (symbol \ "units").text.toInt
				map(ticker) = units
		}
	}
}