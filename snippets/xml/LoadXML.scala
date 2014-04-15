package HackingScala.snippets.xml

import scala.xml._

object LoadXML extends App {
	val xml = XML.loadFile("/Users/lichgo/Dropbox/sourcecode/scala/src/HackingScala/snippets/xml/data.xml")
	
	println("------\\\\function------")
	
	for (hit <- xml \\ "child")
	  println(hit.text)
	 
	println("-------\\function--------")
	 
	for (hit <- xml \ "child")
	  println(hit.text)
	  
	println("-------given class--------")
	
	for (hit <- (xml \\ "child").filter(node => node.attribute("class").exists(attribute => attribute.text == "keypoint")))
	  println(hit.text);
	for (hit <- xml \\ "child") {
	  if ((hit \ "@class").text == "keypoint") println(hit.text)
	}
	
	println("--------source--------")
	
	val childName = "lichgo"
	val strSource =
	  		<parent>
				<child class="small">child01</child>
				<child class="big">child02</child>
				<subparent>
					<child class="wrong">{childName}</child>
				</subparent>
			</parent>
	  
	val source = """
	  		<parent>
				<child class="small">child01</child>
				<child class="big">child02</child>
				<subparent>
					<child class="wrong">wrong_child</child>
				</subparent>
			</parent>"""
	
	val xmlSource = XML.loadString(source)
	// Save to file
	for (hit <- strSource \\ "subparent" \\ "child")
	  scala.xml.XML.save("result.xml", hit, "utf-8", true, null)
	
}