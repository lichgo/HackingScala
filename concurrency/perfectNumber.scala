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
        caller ! sumOfFactorsInRange(lower, upper, candidate)
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
  var start = (new Date).getTime
  println(isPerfect(33550336) + ": " + ((new Date).getTime - start))
  
  start = (new Date).getTime
  println(isPerfectConcurrent(33550336) + ": " + ((new Date).getTime - start))
  
}