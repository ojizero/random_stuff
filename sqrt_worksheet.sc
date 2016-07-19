def abs (x: Double) = if (x < 0) -x else x

def sqrt (x: Double) = {
	// // Newton's Method
	//	def sqrtIter(guess: Double, x: Double): Double =
	//		if (isGoodEnough(guess, x, 100000)) guess
	//		else sqrtIter(iter(guess, x), x)
	//
	//	def isGoodEnough(guess: Double, x: Double, epsilon: Double) = abs(guess * guess - x) < x / epsilon
	//
	//	def iter(guess: Double, x: Double) = (guess + x / guess) / 2
	//
	//	sqrtIter(1.0, x)
	// // abstracted version using currying and higher ordered functions
	fixedPoint(averageDamp(y => x / y))()
}

def averageDamp (f: Double => Double)(x: Double) = (x + f(x)) / 2

def fixedPoint (f: Double => Double, tolerance: Double = 10000)(init: Double = 1): Double = {
	def isClose (next: Double): Boolean = abs(next - init) < next / tolerance

	val next = f(init)
	if (isClose(next)) next
	else fixedPoint(f, tolerance)(next)
}

sqrt(4)

sqrt(1e-6)

fixedPoint(x => 1 + x / 2)(4)
fixedPoint(sqrt)(3)

