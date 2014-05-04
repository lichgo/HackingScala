package HackingScala.projects.assets

import scala.swing._
import event._

object GUI extends SimpleSwingApplication {
	def top = new MainFrame {
		title = "Investment Calculator"

		val label = new Label { text = "------------" }
		val button = new Button { text = "Click me..." }

		contents = new FlowPanel {
			contents += label
			contents += button
		}

		listenTo(button)

		reactions += {
			case ButtonClicked(button) =>
				label.text = "You just clicked."
		}
	}
}