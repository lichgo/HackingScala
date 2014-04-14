object Trait {
	trait Philosophical {
		def philosophize() {
			println("philosophize")
		}
	}
	
	class Frog extends Philosophical {
		override def toString = "green"
	
		// Override trait methods
		override def philosophize() {
			println("overrided by Frog")
		}
	}
	
	val frog = new Frog
	frog.philosophize()
	// Trait as type
	val phil: Philosophical = frog
	frog.philosophize()
	
	
	
	// Sample: Rectangle Object
	class Point(val x: Int, val y: Int)
	
	abstract class Component {
		def topLeft: Point
		def bottom: Point
		//def left: topLeft.x
		//def right = bottomRight.x
	}
	
	class Rectangle(val topLeft: Point, val bottomRight: Point) {
		def left = topLeft.x
		def right = bottomRight.x
	}
	
	trait Rectangle2 {
		def topLeft: Point
		def bottomRight: Point
		def left = topLeft.x
		def right = bottomRight.x
	}
	
	
	// Ordered
	/*
	class Rational(n: Int, d: Int) extends Ordered[Rational] {
	    val number: Int = 1
	    val denom: Int = 4
	  
		def compare(that: Rational) {
			(this.number * that.denom) - (that.number * this.denom)
		}
	}
	val half = new Rational(1, 2)
	val third = new Rational(1, 3)
	half < third
	half >= third
*/
}