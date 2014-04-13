import java.io.FileReader
import java.io.IOException
import java.net.URL
import java.io.FileNotFoundException
import java.net.MalformedURLException

object Controls {
	val args = ("i")
	val arg = List("easy")
	
	var filename =
		if (!arg.isEmpty) args(0)
		else "default"
	
	def greet() { println("hi") }
	greet() == ()	//true
	
	// loop
	// filter
	val filesHere = (new java.io.File(".")).listFiles
	for (file <- filesHere if file.getName.endsWith(".scala"))
		println(file)
	
	// nested loop
	def fileLines(file: java.io.File) =
		scala.io.Source.fromFile(file).getLines.toList
	
	def grep(pattern: String) =
		for {
			file <- filesHere
			if (file.getName.endsWith(".scala"))
			line <- fileLines(file)
			trimmed = line.trim
			if trimmed.matches(pattern)
		} println(file + ": " + trimmed)
	
	// yield
	def scalaFiles = 
		for {
			file <- filesHere
			if (file.getName.endsWith(".scala"))
		} yield file  // scalaFiles = [File]
	
	// try-catch-finally
	try {
		val f = new FileReader("input.txt")
	} catch {
		case ex: FileNotFoundException => //handler
		case ex: IOException => //another handler
	} finally {
		//file.close()
	}
	//return value
	def urlFor(path: String) = 
		try {
			new URL(path)
		} catch {
			case e: MalformedURLException => new URL("http://e.cn")
		}
	
	// finally override the value of previous
	def f(): Int = try { return 1} finally { return 2 }	//2
	def g(): Int = try { 1 } finally { 2 }	// 1
	
	// match and assign value
	val firstArg = if (!args.isEmpty) args(0) else ""
	val friend = 
		firstArg match {
			case "salt" => "tommy"
			case "haha" => "jason"
			case _ => "huh?"
		}
	println(friend)
}