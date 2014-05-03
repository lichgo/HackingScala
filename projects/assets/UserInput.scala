package HackingScala.projects.assets

object UserInput extends App {
  print("Please enter your ticker symbol: ")
  val symbol = readLine
  print("You own: " + symbol)
}