package laziness
import Stream._

sealed trait Stream[+A]{
  def toListRecursive:List[A] = this match {
    case Cons(h,t) => h() :: t().toListRecursive
    case _ => List()
  }


  def toList:List[A] ={
    @annotation.tailrec
    def go(s:Stream[A],acc:List[A]):List[A] = s match {
      case Cons(h,t) => go( t(), h():: acc)
      case _ => acc
    }
    go(this,List()).reverse
  }


  def tolistFast:List[A] = {
    val buf = new collection.mutable.ListBuffer[A]
    @annotation.tailrec
    def go(s: Stream[A]):List[A] = s match{
      case Cons(h,t) =>
        buf += h()
        go(t())
      case _ => buf.toList
    }
    go(this)
  }


  def take(n:Int): Stream[A] = this match {
    case Cons(h,t) if n > 1 => cons(h(),t().take(n-1))
    case Cons(h,_) if n == 1 => cons(h(),empty)
    case _ => empty
  }

  @annotation.tailrec
  final def drop(n: Int) : Stream[A] = this match {
    case Cons(_,t) if n > 0 => t().drop(n-1)
    case _ => this
  }
}
case object Empty extends Stream [Nothing]
case class Cons[+A] (h: () => A , t: () => Stream[A] ) extends  Stream[A]


object Stream {

  def cons[A](hd: => A , tl : => Stream[A]) : Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons( () => head , () => tail )
  }

  def empty[A]:Stream[A] = Empty

  def apply[A] ( as:  A* ) : Stream[A] =
    if( as.isEmpty) empty else cons(as.head , apply(as.tail : _*))
}
