package HackingScala.projects.assets

object UsingXML extends App {
  val XMLFragment = 
    <symbols>
      <symbol ticker="AAPL"><units>200</units></symbol>
      <symbol ticker="IBM"><units>215</units></symbol>
    </symbols>
    
  println(XMLFragment)
  println(XMLFragment.getClass)
  
  val symbolNodes = XMLFragment \ "symbol"
  println(symbolNodes.mkString)
  
  val unitsNodes = XMLFragment \\ "units"
  println(unitsNodes(0).text)
  
  unitsNodes(1) match {
    case <units>{num}</units> => println(num)
  }
  
  println("Ticker\tUnits")
  XMLFragment match {
    case <symbols>{ symbolNodes @ _* }</symbols> =>
      for ( symbolNode @ <symbol>{ _* }</symbol> <- symbolNodes )
        println("%-7s %s".format(symbolNode \ "@ticker", (symbolNode \ "units").text))
  }
}