package Exercise6

import Exercise6.controller.Controller
import Exercise6.model.IntPair
import Exercise6.model.PairDataSet
import Exercise6.view.DotCanvas
import Exercise6.view.TableComponent

/**
 * Controller: wires model and view
 */
fun main() {
    val model = PairDataSet(Pair(10, 10), Pair(20, 20), Pair(30, 30))
    val controller = Controller(model)


    val tableComponent = TableComponent(model)
    tableComponent.addObserver(observer = object : TableComponent.TableEvent {
        override fun pairDeleted(pair: IntPair) {
            controller.remPoint(pair)
        }

        override fun pairModified(old: IntPair, new: IntPair) {
            controller.replacePoint(oldPoint = old, newPoint = new)
        }
    })

    val view = window {
        title = "MVC Exercise"
        size = 600 x 300

        content {
            columns = 1
            +panel {
                +PairDataSetLabel(model)
                +button("add") {
                    val pair = dualPrompt("Values?", "first", "second", "0", "0")
                    pair?.let {
                        model.add(Pair(pair.first.toInt(), pair.second.toInt()))
                    }
                }
                +button("undo") {
                    let {
                        controller.undo()
                    }
                }
            }
            // TODO 2:  DONE react to clicks
            (+DotCanvas(model)).addObserver { b: Pair<Int, Int> ->
                model.add(b)
            }
            // TODO 3: react to edits // TODO 4: react to deletes
            +tableComponent
        }
    }
    view.open()
}


