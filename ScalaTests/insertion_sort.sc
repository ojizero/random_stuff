def insertion_sort /*[T]*/ (list: List[Int /*T*/ ], acc: List[Int] = Nil): List[Int /*T*/ ] =
	list match {
		case Nil => acc
		case x :: xs => insertion_sort(xs, insert(acc, x))
	}

def insert /*[T]*/ (list: List[Int /*T*/ ], elem: Int /*T*/ , acc: List[Int] = Nil): List[Int /*T*/ ] =
	if (list isEmpty) acc ::: (elem :: Nil) else if (list.head > elem) insert(list.tail, elem, acc ::: (list.head :: Nil)) else acc ::: (elem :: list)


insertion_sort(List(2, 5, 8, 7, 1, 2, 2, 3, 5))
