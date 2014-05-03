package HackingScala.projects.assets

import scala.xml._

object ReadWriteXML extends App {
  val stocks = XML.load("src/HackingScala/projects/assets/stocks.xml")
  printf("Loaded %d symbols.", (stocks \\ "symbol").size)
  
  def convertToMap(): Map[String, Int] {
    
  }
  
  def addOne() {
    
  }
}