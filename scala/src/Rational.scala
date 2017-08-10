class Rational (n:Int , d:Int ){
  override def toString: String = n +"/" +d
  require(d!=0)

  private val g:Int = gcd(n.abs ,d.abs)
  val numer :Int = n/g
  val denom :Int = d/g


  private def gcd(a:Int ,b:Int ):Int =
    if( b== 0) a else gcd(b,a%b)

  def add(that: Rational) :Rational =
    new Rational(n* that.denom +  that.numer * d, d  * that.denom)

  def lessThan(that :Rational) =
    numer * denom < that.numer * that.denom

  def max(that :Rational ) =
    if(lessThan(that)) that else this

  def this(n:Int) = this(n,1)

  def * (that :Rational) :Rational =
    new Rational(numer * that.numer , denom * that.denom)

  

}
