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

    val list1 = List(1,2,3,4)
    val list2 = List(5,6,7,8)
    val res:List[Int] = List.appendViaLeft(list1,list2)
    List.PrintList(res)
    val res2:List[Int] = List.appendViaRigt(list1,list2)
    List.PrintList(res2)

    println("sup sub")
    val a1 = List(1,2,3,4,5)
    val a2 = List(2,5)
    val isSub = List.hasSubsequence(a1,a2)
    println(isSub)
  }
}
