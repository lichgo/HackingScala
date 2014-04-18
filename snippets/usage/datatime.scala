package HackingScala.snippets.usage

import java.util.Calendar
import java.text.SimpleDateFormat

object datatime extends App {
  // Using Java SimpleDateFormat class
  // LIKE A FUNCTION, BUT A VALUE
  def onTheFives: Boolean = {
    val today = Calendar.getInstance().getTime()
    val minuteFormat = new SimpleDateFormat("mm")
    val currentMinuteAsString = minuteFormat.format(today)
    try {
      val currentMinute = Integer.parseInt((currentMinuteAsString))
      if (currentMinute % 5 == 0) true
      else false
    } catch {
      case _ => false
    }
  }
  
  println(onTheFives)
}