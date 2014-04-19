//object StringUtils {
//  implicit class StringImprovement(val s: String) {
//    def increment = s.map(c => (c + 1).toChar)
//    def printout = println
//  }
//}

import HackingScala.snippets.usage._

object implicitMethods extends App {
  // Using regular object
  println("HAL".increment) // Compile error
  
  // Using package object
  println("IBM".increase)	// Still error
}