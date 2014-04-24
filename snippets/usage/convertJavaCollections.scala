package HackingScala.snippets.usage

import scala.collection.JavaConversions._

object convertJavaCollections extends App {
  val list = new java.util.ArrayList[Int]()
  list.add(1)
  list.add(2)
  
  list.foreach( println )
}