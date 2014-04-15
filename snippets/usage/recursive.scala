package HackingScala.snippets.usage

import scala.annotation.tailrec

object recursive extends App {
  // --------------------- sum ---------------------
  
  // (1) yields a "java.lang.StackOverflowError" with large lists
  def sum1(ints: List[Int]): Int = ints match {
    case Nil => 0
    case x :: tail => x + sum1(tail)
  }
  
  // (2) tail-recursive
  def sum2(ints: List[Int]): Int = {
    @tailrec
    def sumAccumulator(ints: List[Int], accum: Int): Int = {
      ints match {
        case Nil => accum
        case x :: tail => sumAccumulator(tail, accum + x)
      }
    }
    sumAccumulator(ints, 0)
  }
  
  // (3) stackoverflow.com/questions/12496959/summing-values-in-a-list
  def sum3(ints: List[Int]): Int = {
    if (ints.isEmpty) 0
    else ints.head + sum3(ints.tail)
  }
  
  // (4) reduceLeft
  def sum4(ints: List[Int]) = {
    ints.reduceLeft(_ + _)	  
  }
  
  val list = List.range(1, 100)
  println(sum1(list))
  println(sum2(list))
  println(sum3(list))
  println(sum4(list))
}