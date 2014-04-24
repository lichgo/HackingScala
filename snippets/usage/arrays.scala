package HackingScala.snippets.usage

import scala.collection.mutable.ArrayBuffer

object arrays extends App {
  // Immutable array
  val animals = Array("Cat", "Dog")
  
  val cars = new Array[String](3)
  cars(0) = "bmw"
  cars(1) = "toyoto"
  
  // Mutable array
  var fruits = ArrayBuffer[String]()
  fruits += "Apple"
  fruits += "Banana"
  fruits += "Orange"   
  println(fruits)
  
  // Convert array to string
  val str = fruits.mkString("  |  ")
  println(str)
}