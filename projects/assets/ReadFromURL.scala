package HackingScala.projects.assets

import scala.io.Source
import java.net.URL

object ReadFromURL {
  val source = Source.fromURL(new URL("http://www.scala.lang.org/docu/files/api/index.html"))
  
  //println(source.getLine(3))
  
  val content = source.mkString
}