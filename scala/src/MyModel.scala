object MyModel {
  def abs(n: Int) : Int ={
    if (n < 0)  -n
    else n

  }

  private def formatAbs(x: Int) :String={
    val msg = "The absolute value of %d is %d"
    msg.format(x,abs(x))
  }

  def factorial(n:Int) : Int={
    def go(n:Int , acc:Int): Int =
      if(n<= 0 ) acc
      else go(n-1,n*acc)
    go(n,1)
  }

  def getFibN(n : Int) : Int = {
    def fib(n: Int):Int = {
      if(n<=2) 1
      else fib(n-1)+fib(n-2)
    }
    fib(n)
  }

  def printArrayString(args :Array[String]) :Unit={
    args.foreach(arg => println(arg))
  }

  def findFirst[A] (as: Array[A] , p:A => Boolean): Int = {

    def loop(n : Int) : Int =
      if (n >= as.length) -1
      else if( p(as(n))) n
      else loop(n+1)
    loop(0)
  }

  def isequal(): (Int) => Boolean = {
    def ise(a:Int ) :Boolean ={
      if (a == 1) true
      else false
    }
    ise
  }


  def isSorted[A] (as:Array[A] , gt: (A,A) => Boolean) :Boolean ={
    def go(n:Int) :Boolean = {
      if(n >= as.length - 1) true
      else if ( gt(as(n) , as(n+1))  ) false
      else go(n+1)
    }
    go(0)
  }

  def gt(): (Int, Int) => Boolean = {
    def inner_gt(a:Int , b:Int) :Boolean = {
      if (a>b) true
      else false
    }
    inner_gt
  }


  def gt2(a:Int,b:Int): Boolean = {
    if(a > b) true
    else false
  }


  def partial1[A,B,C](a:A,f:(A,B) => C) :B => C = (b:B) => f(a,b)
  def curry[A,B,C] (f: (A,B) => C) :A => B => C = a => b =>f(a,b)
  def uncurry[A,B,C](f: A => B => C ) :(A,B) =>C = (a,b) => f(a)(b)

  def compose[A,B,C] (f: B => C , g: A => B) :A=>C = a => f(g(a))

  def f(x:Int) :Int = 2*x
  def g(x:Int) :Int = 1+x
  def e(x:Int) :Int = x-5

  def test(a:Int,b:Int):Int = compose(f,g)(a)

  def main(args: Array[String]): Unit = {


//    var list = new Array[Int](3)
//    list(0) = 1;
//    list(1) = 2;
//    list(2) = 3;
//    var index :Int = findFirst[Int](list ,isequal())
//    val is :Boolean = isSorted[Int](list,gt2)
//    println("index = " + index)
//    println("is = " + is)
////    var fg  = f _ andThen  g _
////    var value = fg(2)
////    println("value = " + value )
//    val fg = compose[Int, Int,Int](f,g)
//    var value = fg(2)
//    println("value = " + value)
//
//    val f1 = (x:Double) => math.Pi / 2 - x
//    println(f1(1))

//    val x  = List(1,2,3,4,5) match {
//      case Cons(h,t) => h + List.sum(t)
//      case Cons(x,Cons(2,Cons(4,_))) => x
//      case Nil => 42
//      case Cons(x,Cons(y,Cons(3,Cons(4,_)))) => x+y
//      case _ => 101
//    }
//
//    println("x = " +x )
    val list1 = List(2,3,4,5)
    List.PrintList(list1)
    val x = List.setHead(list1,1)
    List.PrintList(x)
  }
}
