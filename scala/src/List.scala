
sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A] (head: A ,tail :List[A]) extends List[A]

object List {
  def sum(ints :List[Int] ) :Int = ints match {
    case Nil => 0
    case Cons(x,xs) => x + sum(xs)
  }

  def products(ds :List[Double]) :Double = ds match {
    case Nil => 1.0
    case Cons(0.0,_) =>0.0
    case Cons(x, xs) => x * products(xs)
  }

  def tail[A] (l: List[A]) :List[A] = l match {
    case Nil => sys.error("empty List")
    case Cons(_,t) => t
  }

  def setHead[A](list:List[A],a:A):List[A] = list match {
    case Nil => sys.error("empty List")
    case Cons(t,y) =>Cons(a,y)
  }

  def PrintList[A](list:List[A]):Unit = list match {
    case Nil => println("")
    case Cons(t,xs) => {
      print(t+" ");
      PrintList(xs)
    }
  }

  def apply[A] (as : A*) : List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head,apply(as.tail: _*))

}
