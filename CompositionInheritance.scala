object CompositionInheritance {
  def main(args: Array[String]) {
	// abstract class
	abstract class Element {
		def contents: Array[String]
		def height: Int = contents.length
		def width: Int = if (height == 0) 0 else contents(0).length
	
		def above(that: Element): Element = 
			new ArrayElement(this.contents ++ that.contents)
	
		def beside(that: Element): Element = 
			new ArrayElement(
				for (
					(line1, line2) <- this.contents zip that.contents
				) yield line1 + line2
			)
	
		override def toString = contents mkString "\n"
	}
	
	//                ( val contents: Array[String] )
	class ArrayElement(conts: Array[String]) extends Element {
		def contents: Array[String] = conts
		//val contents: Array[String] = conts: also works
	
	}
	
	class Cat {
		val dangerous = true
	}
	class Tiger(
		override val dangerous: Boolean,
		private var age: Int
	) extends Cat
	
	// Invoke super constructor
	class LineElement(s: String) extends ArrayElement(Array(s)) {
		override def width = s.length
		override def height = 1 
	}
	
	// Polymorphism
	class UniformElement(
		ch: Char,
		override val width: Int,
		override val height: Int
	) extends Element {
		private val line = ch.toString * width
		def contents = Array("one", "two")//Array.make(height, line)
	}
	
	// Create a factory for Element
	object Element {
		def elem(contents: Array[String]): Element = 
			new ArrayElement(contents)
	
		def elem(chr: Char, width: Int, height: Int): Element = 
			new UniformElement(chr, width, height)
	
		def elem(line: String): Element = 
			new LineElement(line)
	
		// private sub classes
		//private class ArrayElement
		//private class LineElement
		//private class UniformElement
	}
  }
}