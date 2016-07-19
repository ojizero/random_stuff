def generic (op: (Int, Int) => Int, unitVal: Int)(f: Int => Int)(lowerBound: Int, upperBound: Int): Int = {
	def tailed (acc: Int, lowerBound: Int): Int = if (lowerBound > upperBound) acc else tailed(op(acc, f(lowerBound)), lowerBound + 1)

	tailed(unitVal, lowerBound)
}


def product (f: Int => Int)(lowerBound: Int, upperBound: Int): Int = generic((x, y) => x * y, 1)(f)(lowerBound, upperBound)

def fact (n: Int): Int = product(x => x)(1, n)
