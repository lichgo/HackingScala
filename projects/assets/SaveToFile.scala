package HackingScala.projects.assets

import java.io._

object SaveToFile {
  val writer = new PrintWriter(new File("symbols.txt"))
  writer write "AAPL"
  writer close
}