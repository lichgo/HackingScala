var filename =
	if (!arg.isEmpty) args(0)
	else "default"

def greet() { println("hi") }
greet() == ()	//true

// loop
// filter
val filesHere = (new java.io.File(".")).listFiles
for (file <= filesHere if file.getName.endsWith('.scala'))
	println(file)

// nested loop
def fileLines(file: java.io.File) =
	scala.io.Source.fromFile(file).getLines.toList

def grep(pattern: String) =
	for {
		file <- filesHere
		if (file.getName.endsWith(".scala"))
		line <- fileLines(file)
		trimmed = line.trimm
		if trimmed.matches(pattern)
	} println(file + ": " + trimmed)

// yield
def scalaFiles = 
	for {
		file <- filesHere
		if (file.getName.endsWith('.scala'))
	} yield file  // scalaFiles = [File]

// try-catch-finally
