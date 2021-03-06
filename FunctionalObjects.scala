class Rational(n: Int, d: Int) {
	require(d != 0)

	private val g = gcd(n.abs, d.abs)

	val numer: Int = n / g
	val denom: Int = d / g

	def this(n: Int) = this(n, 1)

	override def toString = n + "/" + d

	def +(that: Rational): Rational = 
		new Rational(
			numer * that.denom + that.numer * denom,
			denom * that.denom
		)
	//def +(i: Int): Rational

	//def lessThan(that: Rational): Rational = 
		//if (this.lessThan(that)) that else this

	private def gcd(a: Int, b: Int): Int = 
		if (b == 0) a else gcd(b, a % b)
}