package HackingScala.snippets.usage

import javax.sound.sampled.DataLine

object getAClass extends App {
	/*
	 * In Java, use Foo.class
	 * In Scala, use classOf[Foo]
	 */
    val info = new DataLine.Info(classOf[ATswitch], null)
    
    // getClass and classOf
    val s = "String"
    s.getClass
    s.getClass.toString
    println(classOf[String])
}