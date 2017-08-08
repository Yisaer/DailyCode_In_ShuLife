sealed trait Tree[+A]
case class Leaf[A](value:A) extends Tree[A]
case class Branch[A](left:Tree[A],right:Tree[A]) extends Tree[A]

object Tree {


  def size[A](tree: Tree[A]):Int = tree match {
    case Leaf(_) => 1
    case Branch(l,r) =>1 +size(l)+size(r)
  }

  def maximum(tree:Tree[Int]):Int = tree match {
    case Leaf(v) => v
    case Branch(l,r) => maximum(l) max maximum(r)
  }

  def depth(tree:Tree[Int]):Int = tree match {
    case Leaf(_) => 0
    case Branch(l,r) => depth(l).max(depth(r)) +1
  }
  def map[A,B](tree:Tree[A])(f:A=>B):Tree[B] = tree match {
    case Leaf(v) => Leaf(f(v))
    case Branch(l,r) => Branch(map(l)(f) , map(r)(f))
  }

  def fold[A,B](t:Tree[A])(f: A=>B )(g:(B,B) =>B):B = t match {
    case Leaf(a) => f(a)
    case Branch(l,r) => g(fold(l)(f)(g),fold(r)(f)(g))
  }

  def sizeViafold[A](t:Tree[A]):Int =
    fold(t)(a => 1 ) (1 +  _ +_ )

  def maximumViafold(t:Tree[Int]):Int =
    fold(t)(a => a )( _ max _ )

  def deph[A](t:Tree[A]):Int =
    fold(t)(_ => 0 )(_ max _ +1)

  def mapViafold[A,B](t:Tree[A])(f:A=>B):Tree[B] =
    fold(t)(a => Leaf(f(a)):Tree[B])(Branch(_,_))

}
