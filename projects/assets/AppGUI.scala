import HackingScala.projects.assets

import scala.swing._
import event._
import scala.actors._
import Actor._
import java.awt.Color

object AppGui extends SimpleSwingApplication {
	def top = mainFrame

	val mainFrame = new MainFrame {
		title = "Assets Calcualtor"

		val dateLabel = new Label { text = "Last updated: ----- " }

		val valuesTable = new Table ( LogicHelper.getInitTableData,
			Array("Ticker", "Units", "Price", "Value") ) {
			showGrid = true,
			gridColor = Color.BLACK
		}

		val updateButton = new Button {  text = "Update" }
		val netAssetLabel = new Label { text = "Net Asset: ????" }

		contents = new BoxPanel(Orientation.Vertical) {
			contents += dateLabel
			contents += valueLabel
			contents += new ScrollPane(valuesTable)
			contents += new FlowPanel {
				contents += updateButton
				contents += netAssetLabel

			}
		}

		listenTo(updateButton)

		reactions += {
			case ButtonClicked(button) =>
				button.enabled = false
				LogicHlper fetchPrice uiUpdater
		}

		val uiUpdater = new Actor {
			def act = {
				react {
					case (symbol: String, units: Int, price: Double, value: Double) =>
						updateTable(_, _, _, _)
					case netAsset =>
						netAssetLabel.text = "Net Asset: " + netAsset
						dateLabel.text = "Last updated: " + new java.util.Date()
						updateButton.enabled = true;
				}
			}

			override protected def scheduler() = new SingleThreadedScheduler
		}
		uiUpdater.start()

		def updateTable(symbol: String, units: Int, price: Double, value: Double) {
			for (i <- 0 until valuesTable.rowCount) {
				if (valuesTable(i, 0) == symbol) {
					valuesTable(i, 2) = price
					valuesTable(i ,3) = value
				}
			}
		}
	}

}