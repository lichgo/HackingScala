package HackingScala.projects.assets

import scala.xml._

object ReadWriteXML extends App {
  val stocks = XML.load("src/HackingScala/projects/assets/stocks.xml")
  printf("Loaded %d symbols.", (stocks \\ "symbol").size)

  val stockUnitsMap = convertToMap(stocks)
  val updatedXML =
    <symbols>
      { stockUnitsMap.map { addOne } }
    </symbols>

  XML.save("stocks2.xml", updatedXML)
  
  def convertToMap(stocks: scala.xml.Elem): Map[String, Int] =
    (Map[String, Int]() /: (stocks \ "symbol")) {
    	(map, symbolNode) =>
    		val ticker = (symbolNode \ "@ticker") toString
    		val units = (symbolNode \ "units") text toInt
    		map(ticker) = units
    }

  def addOne(element: (String, Int)) = // map's element is tuple
  	val (ticker, units) = element
  	<symbol ticker={ticker}>
  		<units>{units + 1}</units>
  	</symbol>
}