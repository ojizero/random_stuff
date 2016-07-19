// Testing the Huffman `times` method

def times (chars: List[Char], acc: List[(Char, Int)] = Nil): List[(Char, Int)] = {
	def modElem (list: List[(Char, Int)], elem: (Char, Int), acc: List[(Char, Int)] = Nil): List[(Char, Int)] =
		list match {
			case Nil => elem :: acc
			case c :: cs => if (c._1 == elem._1) acc ::: ((c._1, c._2 + elem._2) :: cs) else modElem(cs, elem, c :: acc)
		}

	chars match {
		case Nil => acc
		case c :: cs => times(cs, modElem(acc, (c, 1)))
	}
}

times(List('a', 'b', 'a'))

