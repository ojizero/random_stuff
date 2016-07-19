/**
  * Created by oji on 7/10/16.
  */

/**
  * This is a pure object-oriented implementation of a very simplified Natural Numbers implementation
  * From quick in-lecture quiz (Lecture 4.1 - Objects Everywhere from the 'Functional Programming Principles in Scala' course from Coursera)
  * This approach is named Peano Numbers
  */

package naturals

// simplified interface for the Natural numbers
/*trait */ abstract class Nat {
	def isZero: Boolean

	def predecessor: Nat

	def successor: Nat = new Successor(this)

	def + (that: Nat): Nat

	def - (that: Nat): Nat

	def toInt (acc: Int = 0): Int = if (isZero) acc else this.predecessor.toInt(acc + 1)

	override def toString: String = toInt().toString
}

object Zero extends Nat {
	override def isZero: Boolean = true

	override def + (that: Nat): Nat = that

	override def - (that: Nat): Nat = if (that isZero) this else throw new NoSuchElementException("Negative natural numbers don't exist !")

	override def predecessor: Nat = throw new NoSuchElementException("Zero has no natural predecessor !")
}

class Successor (val predecessor: Nat) extends Nat {
	override def isZero: Boolean = false

	override def + (that: Nat): Nat = if (that isZero) this else new Successor(this + that.predecessor)

	override def - (that: Nat): Nat = if (that isZero) this else this.predecessor - that.predecessor

}

object Main extends App {
	// 5 + 2
	val five = new Successor(new Successor(new Successor(new Successor(Zero.successor))))
	val two = new Successor(Zero.successor)

	println(five + " + " + two + " = " + (five + two))
	println(five + " - " + two + " = " + (five - two))
}
