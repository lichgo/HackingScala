package HackingScala.concurrency

import scala.actors.Scheduler
import scala.actors.Actor
import scala.actors.Actor.actor
import scala.actors.scheduler.SingleThreadedScheduler

object singleThreadedInMain extends App {
  Scheduler.impl = new SingleThreadedScheduler
  
  // Thesse three threads run in the same main thread
  println("MAIN: " + Thread.currentThread());
  actor { println("Actor1: " + Thread.currentThread()) }
  actor { println("Actor2: " + Thread.currentThread()) }
  
  // --- Rewrite a actor factory to make it run in single thread
//  trait SingleThreadedActor extends Actor {
//    override protected def scheduler() = new SingleThreadedScheduler
//  }
}