// Companion class
class CheckSumAccumulator {
	private var sum = 0
	def add(b: Byte) { sum += b }	//return Unit
	def checksum(): Int = ~(sum & 0xFF) + 1
	def getStr() = { "returned string" }	// with =, return string
}

// Companion object (Singleton object)
import scala.collection.mutable.Map
object CheckSumAccumulator {
	private val cache = Map[String, Int]()
	def calculate(s: String): Int =
		if (cache.contains(s))
			cache(s)
		else {
			val acc = new CheckSumAccumulator
			for (c <- s)
				acc.add(c.toByte)
			val cs = acc.checksum()
			cache += (s -> cs)
			cs
		}
}
