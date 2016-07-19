// instead of CMP we could use ord from math.Ordering[T]
// then we can use the `implicit` parameter for cmp and avoid even explicitly stating it

def mergeSort[T] (list: List[T])(cmp: (T, T) => Boolean = (x: T, y: T) => true /*if always true doesn't change, if always false reverses list*/): List[T] = {
	def merge (l1: List[T], l2: List[T], acc: List[T] = Nil): List[T] = (l1, l2) match {
		case (Nil, Nil) => acc
		case (Nil, _) => acc ::: l2
		case (_, Nil) => acc ::: l1
		case (x :: xs, y :: ys) =>
			if (cmp(x, y)) merge(xs, l2, acc ::: (x :: Nil))
			else merge(l1, ys, acc ::: (y :: Nil))
	}

	list match {
		case x :: Nil => list
		case Nil => list
		case _ =>
			val (first, second) = list splitAt (list.length / 2)
			merge(mergeSort(first)(cmp), mergeSort(second)(cmp))
	}
}

mergeSort(List(2, 5, 8, 7, 1, 2, 2, 3, 5))((x, y) => x < y)
