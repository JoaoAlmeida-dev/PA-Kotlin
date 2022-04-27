package Exercise6.view

import Exercise6.*
import Exercise6.controller.Controller
import Exercise6.model.IntPair
import Exercise6.model.PairDataSet
import java.awt.GridLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField


/**
 * View for listing the int pairs in an editable table
 */
class TableComponent(model: PairDataSet) : JPanel(), IObservable<TableComponent.TableEvent> {

    interface TableEvent {
        fun pairModified(old: IntPair, new: IntPair) {}

        // TODO: observable event: delete button click
        fun pairDeleted(pair: IntPair) {}
    }

    override val observers: MutableList<TableEvent> = mutableListOf()

    init {
        layout = GridLayout(0, 1)
        model.forEach {
            addPair(it)
        }
        // TODO 4: register observer in the model
        model.addObserver { a: PairDataSet.EventType, b: Pair<Int, Int>, c: Pair<Int, Int>? ->
            when (a) {
                PairDataSet.EventType.ADD -> addPair(b)
                PairDataSet.EventType.REMOVE -> removePair(b)
                PairDataSet.EventType.REPLACE -> c?.let { replacePair(it, b) }
            }
        }

    }

    private fun addPair(pair: IntPair) {
        add(PairComponent(pair))
        revalidate()
        repaint()
    }

    fun removePair(pair: IntPair) {
        val find = components.find { it is PairComponent && it.matches(pair) }
        find?.let {
            remove(find)
        }
        revalidate()
        repaint()
    }

    fun replacePair(old: IntPair, new: IntPair) {
        val find = components.find { it is PairComponent && it.matches(old) } as? PairComponent
        find?.let {
            find.modify(new)
        }
    }

    inner class PairComponent(var pair: IntPair) : JComponent() {
        val first = JTextField("${pair.first}")
        val second = JTextField("${pair.second}")

        inner class MouseClick(val first: Boolean) : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                val newPair = dualPrompt("new values", "first", "second", pair.first.toString(), pair.second.toString())
                newPair?.let {

                    notifyObservers {
                        it.pairModified(pair, IntPair(newPair.first.toInt(), newPair.second.toInt()))
                    }
                }
            }
        }

        init {
            layout = GridLayout(0, 3)

            first.isEnabled = false
            first.addMouseListener(MouseClick(true))
            add(first)

            second.isEnabled = false
            second.addMouseListener(MouseClick(false))
            add(second)

            add(button("delete") {
                // TODO 4: fire event
                notifyObservers { it.pairDeleted(pair) }
                println("remove pair $pair")
                removePair(pair)
            })
        }

        fun modify(new: Pair<Int, Int>) {
            pair = new
            first.text = "${new.first}"
            second.text = "${new.second}"
        }

        fun matches(p: Pair<Int, Int>) = pair == p
    }
}
