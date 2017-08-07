object ListTest {


  def f(n: Int) : Boolean = {
    if ( n == 1) true
    else false
  }
  def main(args: Array[String]): Unit = {
    val xs :List[Int] = List(2,3,4,5)
    val ex1 = List.dropWhileHof(xs)(x => x<4)
    List.PrintList(ex1)
    val isgt1 = List.gt1(xs)
    println(isgt1)
    val list :List[Int] = List(1,2,3,4,5)
    val size :Int = List.length(list)
    println("size = " + size)
    val len2 = List.lengthLeft(List(1,2,3,4,5))
    println("size left = " + len2 )

  }
}
