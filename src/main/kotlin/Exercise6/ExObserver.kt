package Exercise6

import Exercise6.controller.Controller
import Exercise6.model.IntPair
import Exercise6.model.PairDataSet
import javax.swing.*


/**
 * Interface for observable objects
 */
interface IObservable<O> {

    // Implementers have to provide this property
    val observers: MutableList<O>

    fun addObserver(observer: O) {
        observers.add(observer)
    }

    fun removeObserver(observer: O) {
        observers.remove(observer)
    }

    fun notifyObservers(handler: (O) -> Unit) {
        observers.toList().forEach { handler(it) }
    }
}


// TODO 1: DONE - make this class react to changes in the model
class PairDataSetLabel(model: PairDataSet) : JLabel() {
    init {
        text = "$model"
        model.addObserver { _, _, _ ->
            text = "$model"
        }
    }
}




