package HackingScala.concurrency

import java.util.Date

object perfectNumber extends App {
  // Sequential
  def sumOfFactors(number: Int) = {
    (0 /: (1 to number)) { (sum, i) => if (number % i == 0) sum + i else sum }
  }
  def isPerfect(candidate: Int) = 2 * candidate == sumOfFactors(candidate)
  
  // Concurrent
  import scala.actors.Actor._
  def sumOfFactorsInRange(lower: Int, upper: Int, number: Int) = {
    (0 /: (lower to upper)) { (sum, i) => if (number % i == 0) sum + i else sum }
  }
  def isPerfectConcurrent(candidate: Int) = {
    val RANGE = 1000000
    val numOfPartitions = (candidate.toDouble / RANGE).ceil.toInt
    val caller = self	// main thread
    
    for (i <- 0 until numOfPartitions) {
      val lower = i * RANGE + 1
      val upper = candidate min (i + 1) * RANGE
      
      actor {
        caller ! sumOfFactorsInRange(lower, upper, candidate)	//send msg to caller
      }
    }
    
    val sum = (0 /: (0 until numOfPartitions)) {
      (partialSum, i) => receive {
        case sumInRange: Int => partialSum + sumInRange
      }
    }
    
    2 * candidate == sum
  }
  
  // Test
  var startTime = (new Date).getTime
  println(isPerfect(33550336) + ": " + ((new Date).getTime - startTime))
  
  startTime = (new Date).getTime
  println(isPerfectConcurrent(33550336) + ": " + ((new Date).getTime - startTime))
  
  // Using high-order function
  def countPerfectNumberInRange(start: Int, end: Int, isPerfectFinder: Int => Boolean) = {
    val startTime = System.nanoTime()
    
    val numberOfPerfectNumbers = (0 /: (start to end)) {
      (count, candidate) => if (isPerfectFinder(candidate)) count + 1 else count
    }
    
    val endTime = System.nanoTime()
    
    println("Count: " + numberOfPerfectNumbers + "(" + (endTime - startTime) / 10000000000.0 + "s)")
  }
  
  //Test
  val start = 335503
  val end = 336903
  countPerfectNumberInRange(start, end, isPerfect)
  countPerfectNumberInRange(start, end, isPerfectConcurrent)
}