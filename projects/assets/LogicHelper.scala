package HackingScala.projects.assets

import scala.actors._
import Actor._

object LogicHelper {
  val stockUnitMap = StockPriceSpider.getStockUnitsMap
  
  def genInitTableData: Array[Array[Any]] = {
    val datasource = new Array[Array[Any]](0)
    (datasource /: stockUnitMap) {
      (data, element) =>
        val (symbol, units) = element
        data ++ Array(List(symbol, units, "?", "?").toArray)
    }
  }
  
  // intermediate actor bridging [UI thread] and [fetching threads]
}