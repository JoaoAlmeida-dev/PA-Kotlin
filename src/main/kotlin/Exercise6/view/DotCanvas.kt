package Exercise6.view

import Exercise6.IObservable
import Exercise6.controller.Controller
import Exercise6.model.IntPair
import Exercise6.model.PairDataSet
import java.awt.Color
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.JComponent

// TODO 2: DONE - make these objects observable with respect to mouse clicks - observers should obtain the clicked coordinate
class DotCanvas(private val model: PairDataSet) : JComponent(),
    IObservable<(IntPair) -> Unit> {

    public override fun paintComponent(g: Graphics) {
        g.color = Color.BLACK
        for (d in model) {
            g.fillOval(d.first, d.second, 10, 10)
        }
    }

    init {
        border = BorderFactory.createLineBorder(Color.BLACK)

        // reacts to changes in the model
        model.addObserver { _, _, _ ->
            repaint()
        }

        addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                // TODO: DONE - fire click event
                println("mouse click: ${e.x}, ${e.y}")
                notifyObservers {
                    it(IntPair(e.x, e.y))
                }
            }
        })
    }

    override val observers: MutableList<(IntPair) -> Unit> = mutableListOf()
}