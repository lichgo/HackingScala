package HackingScala.projects.assets

import scala.actors._
import Actor._

object LogicHelper {
  val stockUnitsMap = StockPriceSpider.getStockUnitsMap
  
  def genInitTableData: Array[Array[Any]] = {
    val datasource = new Array[Array[Any]](0)
    (datasource /: stockUnitsMap) {
      (data, element) =>
        val (symbol, units) = element
        data ++ Array(List(symbol, units, "?", "?").toArray)
    }
  }
  
  // intermediate actor bridging [UI thread:updater] and [fetching threads]
  def fetchPrice(updater: Actor) = actor {
    val caller = self
    
    stockUnitsMap.keys.foreach {
      symbol => actor { caller ! (symbol, StockPriceSpider.getLatestClosingPrice(symbol)) }
    }
    
    // receive stockUnitsMap.size times
    val netWorth = (0.0 /: (1 to stockUnitsMap.size)) {
      (worth, index) => {
        receiveWithin(10000) {
          case (symbol: String, latestClosingPrice: Double) =>
            val units = stockUnitsMap(symbol)
            val value = units * latestClosingPrice
            updater ! (symbol, units, latestClosingPrice, value)
            worth + value
        }
      }
    }
    
    updater ! netWorth
  }
  
}