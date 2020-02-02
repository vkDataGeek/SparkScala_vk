object LearningScala3 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(225); 
  // Functions
  
  // Format is def <function name>(parameter name: type...) : return type = { expression }
  // Don't forget the = before the expression!
  def squareIt(x: Int) : Int = {
  	x * x
  };System.out.println("""squareIt: (x: Int)Int""");$skip(43); 
  
  def cubeIt(x: Int): Int = {x * x * x};System.out.println("""cubeIt: (x: Int)Int""");$skip(26); 
  
  println(squareIt(2));$skip(24); 
  
  println(cubeIt(2));$skip(124); 
  
  // Functions can take other functions as parameters
  
  def transformInt(x: Int, f: Int => Int) : Int = {
  	f(x)
  };System.out.println("""transformInt: (x: Int, f: Int => Int)Int""");$skip(42); 
  
  val result = transformInt(2, cubeIt);System.out.println("""result  : Int = """ + $show(result ));$skip(19); 
  println (result);$skip(209); val res$0 = 
  
  // "Lambda functions", "anonymous functions", "function literals"
  // You can declare functions inline without even giving them a name
  // This happens a lot in Spark.
  transformInt(3, x => x * x * x);System.out.println("""res0: Int = """ + $show(res$0));$skip(34); val res$1 = 
  
  transformInt(10, x => x / 2);System.out.println("""res1: Int = """ + $show(res$1));$skip(50); val res$2 = 
  
  transformInt(2, x => {val y = x * 2; y * y});System.out.println("""res2: Int = """ + $show(res$2));$skip(410); 
  
  // This is really important!
  
  // EXERCISE
  // Strings have a built-in .toUpperCase method. For example, "foo".toUpperCase gives you back FOO.
  // Write a function that converts a string to upper-case, and use that function of a few test strings.
  // Then, do the same thing using a function literal instead of a separate, named function.
  
  def strUP(s: String): String = {
  s.toUpperCase()
  };System.out.println("""strUP: (s: String)String""");$skip(27); 
  
  println(strUP("foo"));$skip(79); 
  
  def transformStr(s: String, sp: String => String) : String ={
  sp(s)
  };System.out.println("""transformStr: (s: String, sp: String => String)String""");$skip(31); val res$3 = 
  
 transformStr("foo", strUP);System.out.println("""res3: String = """ + $show(res$3));$skip(46); val res$4 = 
  
 transformStr("foo", s => s.toUpperCase());System.out.println("""res4: String = """ + $show(res$4));$skip(45); val res$5 = 
 
 transformStr("foo", x => x.toUpperCase());System.out.println("""res5: String = """ + $show(res$5))}
 
  /*def transformStr(s: String, sp1: String => String, sp: String => String) : String ={
	val stuff = (sp(s),sp1(s))
	println(stuff._1)
  }
  
  transformStr("Foo", s => s.toLowerCase(), s => s.toLowerCase())*/
  
  
}
