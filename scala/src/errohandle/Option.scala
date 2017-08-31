package errohandle

sealed trait Option[+A]
{
  def map[B](f: A => B): Option[B] =
    this match {
      case None => None
      case Some(a) => Some(f(a))
    }

  def getOrElse[B>:A](default: => B): B =
    this match {
      case None => default
      case Some(a) => a

    }

  def flatMap[B](f: A => Option[B]): Option[B] =
    map(f) getOrElse None

  /*
  Of course, we can also implement `flatMap` with explicit pattern matching.
  */
  def flatMap_1[B](f: A => Option[B]): Option[B] = this match {
    case None => None
    case Some(a) => f(a)
  }

  def orElse[B>:A](ob: => Option[B]): Option[B] =
    this map (Some(_)) getOrElse ob

  /*
  Again, we can implement this with explicit pattern matching.
  */
  def orElse_1[B>:A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case _ => this
  }

  def filter(f: A => Boolean): Option[A] = this match {
    case Some(a) if f(a) => this
    case _ => None
  }
  /*
  This can also be defined in terms of `flatMap`.
  */
  def filter_1(f: A => Boolean): Option[A] =
    flatMap(a => if (f(a)) Some(a) else None)
}
case class Some[+A] (get :A) extends Option[A]
case object None extends Option[Nothing]

object Option {

  // for average
  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)

  // 第一个mean求平均值，然后用flagMap将m值代入，遍历xs，求出方差
  def variance(xs: Seq[Double]): Option[Double] =
    mean(xs) flatMap (m => mean(xs.map(x => math.pow(x - m, 2))))


  // b map( => f(aa,bb)) 这里获得a的值b得值变成c 然后包装为Option[c]
  // 然后交给前面的aa = > 从而变成 a flatMap ( aa => Option[C])
  def map2[A,B,C](a:Option[A] , b: Option[B])(f: (A,B) => C ): Option[C]
   = a flatMap(aa => b map ( bb => f(aa,bb)))

  def sequence[A](a :List[Option[A]]):Option[List[A]]
    = a match {
    case Nil => Some(Nil)
    case h :: t => h flatMap( hh => sequence(t) map(hh :: _) )
  }

  def sequenceAll[A](a :List[Option[A]]):Option[List[A]]
  = a match {
    case Nil => Some(Nil)
    case h :: t  =>
      if( h != None) {
      println("h = "+ h)
      h flatMap( hh => sequenceAll(t) map(hh :: _) )
    } else{
      println(" None " + h  )
      val x = sequenceAll(t)
      println(x)
      x
    }
  }



  def main(args: Array[String]): Unit = {

//
//    val x = Some(1)
//    val y = x flatMap ( i => Some(i+1))
//    println(y)

    val list1 = List(Some(1),None, Some(2))
    val list2 = sequenceAll(list1)
    println(list2)

//    val evenif = (x:Int) => if( x% 2 == 0) {
//      println("here2")
//      Some(x)
//    } else {
//      println("here5")
//      None
//    }
//    val x :Option[Int] = Some(5)
//    val y  = x.flatMap(evenif)
//    println(y)
//
//
//    val list1  = List(None)
//    println(list1)
//    println(sequence(list1))

    //    val listd1 :Seq[Double] = Seq()
//    val res = variance(listd1)
//    println(res)
  }

}
