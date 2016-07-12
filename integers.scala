/**
  * Created by oji on 7/11/16.
  */

/**
  * A semi-functioning, purely-bodged spaghetti of code, for a pure object-oriented implementation of Integers
  * DISCLAIMER, the code is a spaghetti of a mess, and may not be the most efficient or pretty, or cleaned up of tests scraps xD
  **/


abstract class Integer {
	def isZero: Boolean

	def isNegative: Boolean

	def isPositive: Boolean = !isNegative

	def predecessor: Integer

	def successor: Integer // = new Successor(this)

	def + (that: Integer): Integer

	def toNegative (acc: Integer = Zero): Integer

	def toPositive (acc: Integer = Zero): Integer

	def - (that: Integer): Integer = if (that isZero) this else this + (-that)

	def * (that: Integer): Integer

	def mult (that: Integer, acc: Integer): Integer

	def / (that: Integer): Integer = ???

	def % (that: Integer): Integer = ???

	def unary_- : Integer

	def toInt (acc: Int): Int

	def toInt (acc: Int = 0, op: (Int, Int) => Int): Int // = if (isZero) acc else this.predecessor.toInt(op(acc, 1), op)

	override def toString: String = "Int(" + toInt(0) + ")"

	def > (that: Integer): Boolean = (this - that) isPositive

	def < (that: Integer): Boolean = (this - that) isNegative

	def == (that: Integer): Boolean = (this - that) isZero

	def >= (that: Integer): Boolean = (this > that) || (this == that)

	def <= (that: Integer): Boolean = (this < that) || (this == that)
}

object Zero extends Integer {
	override def isZero: Boolean = true

	override def isNegative: Boolean = false


	override def successor: Integer = new Positive(this)

	override def predecessor: Integer = new Negative(this)


	override def toInt (acc: Int): Int = 0

	override def + (that: Integer): Integer = that

	def unary_- : Integer = this

	override def - (that: Integer): Integer = -that

	override def toNegative (acc: Integer = Zero): Integer = this

	override def toPositive (acc: Integer = Zero): Integer = this

	override def toInt (acc: Int, op: (Int, Int) => Int): Int = 0

	override def * (that: Integer) = Zero

	override def mult (that: Integer, acc: Integer) = Zero
}

class Positive (val predecessor: Integer) extends Integer {
	override def isZero: Boolean = false

	override def isNegative: Boolean = false


	override def successor: Integer = new Positive(this)


	override def + (that: Integer): Integer = if (that isZero) this else if (that isNegative) this - (-that) else new Positive(this + that.predecessor)

	def toNegative (acc: Integer = Zero): Integer = if (this.predecessor isZero) acc.predecessor else this.predecessor.toNegative(acc.predecessor)

	def toPositive (acc: Integer = Zero): Integer = this

	def unary_- : Integer = toNegative()

	override def - (that: Integer): Integer = if (that isZero) this else if (that isNegative) this + (-that) else this.predecessor - that.predecessor

	override def toInt (acc: Int): Int = if (this.predecessor.isZero) acc + 1 else this.predecessor.toInt(acc + 1) //toInt(0, (x, y) => x + y)

	override def > (that: Integer): Boolean = if (that.isNegative || that.isZero) true else super.>(that)

	override def < (that: Integer): Boolean = if (that.isNegative || that.isZero) false else super.<(that)

	override def toInt (acc: Int, op: (Int, Int) => Int): Int = ???

	override def * (that: Integer): Integer = if (that isNegative) -mult(-that, this) else mult(that, this)

	override def mult (that: Integer, acc: Integer): Integer = if (that.predecessor isZero) acc else mult(that - Zero.successor, acc + this)
}

class Negative (val successor: Integer) extends Integer {
	override def isZero: Boolean = false

	override def isNegative: Boolean = true

	override def predecessor: Integer = new Negative(this)


	override def toInt (acc: Int): Int = toInt(0, (x, y) => x - y)


	override def + (that: Integer): Integer = if (that isZero) this else if (that isNegative) -((-this) + (-that)) else that + this

	override def - (that: Integer): Integer = if (that isZero) this else if (that isNegative) (-that) + this else -((-this) + that) //this + (-that)

	def toPositive (acc: Integer = Zero): Integer = if (this.successor isZero) acc.successor else this.successor.toPositive(acc.successor)

	def toNegative (acc: Integer = Zero): Integer = this


	def unary_- : Integer = toPositive()

	override def > (that: Integer): Boolean = if (that.isPositive || that.isZero) false else super.>(that)

	override def < (that: Integer): Boolean = if (that.isPositive || that.isZero) true else super.<(that)

	override def toInt (acc: Int, op: (Int, Int) => Int): Int = if (this.successor.isZero) acc - 1 else this.successor.toInt(op(acc, 1), op)

	override def * (that: Integer): Integer = if (that isPositive) mult(that, this) else -mult(-that, this)

	override def mult (that: Integer, acc: Integer): Integer = if (that.predecessor isZero) acc else mult(that - Zero.successor, acc + this)
}

object MainInts extends App {
	val five = new Positive(new Positive(new Positive(new Positive(Zero.successor))))
	val minus_two = new Negative(Zero.predecessor)
	val positive_two = new Positive(Zero.successor)

	println(minus_two * minus_two)
}
