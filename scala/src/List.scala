
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

  def drop[A] (list :List[A] , n :Int) :List[A] = {
    if (n <= 0 ) list
    else list  match {
      case Nil => Nil
      case Cons(_,t) => drop(t,n-1)
    }
  }

  def dropWhile[A] (list :List[A] , f: A=> Boolean ): List[A] = list match {
    case Cons(h,t) if f(h) => dropWhile(t,f)
    case _ => {
      PrintList(list)
      list
    }
  }



  def dropIf[A] (list :List[A] , f: A=> Boolean ): List[A] = list match {
    case Nil => Nil
    case Cons(a,t) if f(a) => dropIf(t,f)
    case Cons(a,Cons(b,c)) if f(b) => dropIf(Cons(a,c),f)
    case Cons(a,Cons(b,c)) if !f(b) => Cons(a,Cons(b,dropIf(c,f)))
  }

  def append[A] (a1:List[A] ,a2:List[A]) :List[A] = a1 match{
    case Nil => a2
    case Cons(h,t) =>Cons(h,append(t,a2))
  }

  def init0[A] (list :List[A] ):List[A] = list match {
    case Nil => Nil
    case Cons(a,Cons(b,c)) if c == Nil => Cons(a,Nil)
    case Cons(a,Cons(b,c)) if c != Nil => Cons(a,init0(Cons(b,c)))
  }
  def init1[A] (list:List[A]):List[A] = list match {
    case Nil => sys.error("empty List")
    case Cons(_,Nil) => Nil
    case Cons(h,t) =>Cons(h,init1(t))
  }
  def init2[A] (list :List[A]):List[A] = {
    import collection.mutable.ListBuffer
    val buf = new ListBuffer[A]

    def go(cur:List[A]):List[A] = cur match {
      case Nil => sys.error("empty List")
      case Cons(_,Nil) =>List(buf.toList: _*)
      case Cons(h,t) => {
        buf += h
        go(t)
      }
    }
    go(list)
  }

  def dropWhileHof[A] (as:List[A])(f: A=>Boolean) :List[A] = as match {
    case Cons(h,t) if f(h) => dropWhileHof(t)(f)
    case _ => as
  }

  def foldRight[A,B] (as :List[A] ,z:B ) (f:(A,B) => B) :B = as match{
    case Nil => z
    case Cons(x,xs) => f(x,foldRight(xs,z)(f))
  }

  def sum2(ns:List[Int]) =
    foldRight(ns,0)((x , y)=>x + y)

  def gt1(list:List[Int]) =
    foldRight(list,true)((x,y) => (x>1)&y )

  def length[A] (as: List[A]):Int =
    foldRight(as ,0)((_,y) => y+1)

  def foldLeft[A,B] (as :List[A], z:B) (f: (B,A) => B ) :B  = as match {
    case Nil => z
    case Cons(h,t) => foldLeft(t,f(z,h))(f)
  }

  def sumleft (l: List[Int]) = foldLeft(l,0)((B,A)=> B + A)
  def productLeft(list :List[Int]) = foldLeft(list,0)((B,A) => B+A )
  def lengthLeft(list:List[Int])  = foldLeft(list,0)( (B,_ ) => B+1)

  def reverse[A](list :List[A]): List[A] =
    foldLeft(list, List[A]())((acc,h) => Cons(h,acc))

  def foldRight2[A,B](as:List[A],acc :B )(f : (A,B)=>B ):B =
    foldLeft(reverse(as), acc)((B,_) =>B)

//  def foldLeftViafoldRigt[A,B](l:List[A] ,acc :B )(f: (A,B)=>B):B =
//    foldRight(l,(b:B) => b )((g,a) => b => g(f(a,b)))(acc)

  def appendViaRigt[A](l:List[A] , r : List[A]):List[A] =
    foldRight(l,r)((lt,r) =>Cons(lt,r))

  def appendViaLeft[A](l: List[A] , r:List[A]):List[A] =
    foldLeft(l,r)((r,lt) => Cons(lt,r))


  def apply[A] (as : A*) : List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head,apply(as.tail: _*))


}
