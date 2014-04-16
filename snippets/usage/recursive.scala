package HackingScala.snippets.usage

import scala.annotation.tailrec

object recursive extends App {
  // --------------------- sum ---------------------
  
  // (1) yields a "java.lang.StackOverflowError" with large lists
  def sum1(ints: List[Int]): Int = 
    ints match {
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
  
  // --------------------- max ---------------------
  
  // (1) match
  def max1(ints: List[Int]): Int = {
    @tailrec
    def maxAccum(ints: List[Int], theMax: Int): Int = {
      ints match {
        case Nil => theMax
        case x :: tail =>
          	val newMax = if (x > theMax) x else theMax
          	maxAccum(tail, newMax)
      }
    }
    maxAccum(ints, Integer.MIN_VALUE)
  }
  
  // (2) if/else
  def max2(ints: List[Int]): Int = {
    @tailrec
    def maxAccum(ints: List[Int], theMax: Int): Int = {
      if (ints.isEmpty) {
        return theMax
      } else {
        val newMax = if (ints.head > theMax) ints.head else theMax
        maxAccum(ints.tail, newMax)
      }
    }
    maxAccum(ints, Integer.MIN_VALUE)
  }
  
  println(max1(list))
  println(max2(list))
  
  // --------------------- fibonacci ---------------------
  
  // (1)Directly print out
  def fib1(prevPrev: Int, prev: Int) {
    val next = prevPrev + prev
    printf(next + " ")
    if (next > 100) return	//return (), next is (), and print ()
    fib1(prev, next)
  }
  
  // (2) Basic recursive
  def fib2(n: Int): Int = {
    if (n == 0) 1
    else n * fib2(n - 1)
  }
  
  // (3) Tail-recursive
  def fib3(n: Long): Long = {
    @tailrec
    def factorialAccumulator(acc: Long, n: Long): Long = {
      if (n == 0) acc
      else factorialAccumulator(acc * n, n - 1)
    }
    factorialAccumulator(1, n)
  }
  
  println(fib1(1, 2));
  println(fib2(5));
  println(fib3(5));
}