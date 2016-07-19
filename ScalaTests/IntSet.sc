

abstract class IntSet {
	def incl (x: Int): IntSet

	def contains (x: Int): Boolean

	def union (other: IntSet): IntSet
}


object Empty extends IntSet {
	def contains (x: Int): Boolean = false

	def incl (x: Int): IntSet = new NonEmpty(x, Empty, Empty)

	def union (other: IntSet): IntSet = other

	override def toString = "."
}

class NonEmpty (elem: Int, left: IntSet, right: IntSet) extends IntSet {

	def this (x: Int) = this(x, Empty, Empty)

	def contains (x: Int): Boolean =
		if (x < elem) left contains x
		else if (x > elem) right contains x
		else true

	def incl (x: Int): IntSet =
		if (x < elem) new NonEmpty(elem, left incl x, right)
		else if (x > elem) new NonEmpty(elem, left, right incl x)
		else this

	def union (other: IntSet): IntSet =
		((left union right) union other) incl elem

	override def toString = "{" + left + elem + right + "}"

}


val test = new NonEmpty(5, new NonEmpty(4, Empty, Empty), new NonEmpty(7, Empty, Empty))

val ha = new NonEmpty(3, Empty, new NonEmpty(4, Empty, Empty))

test union ha


trait T {
	def x: Int

	def y: Int = 0
}

val j: T = new T {
	override def x: Int = 1
}

abstract class Y {
	def h: Double = 5
}

val g: Y = new Y {}

/////////////////

class Blah extends Y {

}

val b: Blah = new Blah

class Bruh extends T {
	def x: Int = 1
}

val k: Bruh = new Bruh
