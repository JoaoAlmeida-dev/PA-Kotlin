package Exercise6.model

import Exercise6.IObservable

typealias IntPair = Pair<Int, Int>


/**
 * Set of integer pairs (no duplicates), serving as the model
 */
class PairDataSet(vararg pairs: IntPair) : Iterable<IntPair>,
    IObservable<(PairDataSet.EventType, IntPair, IntPair?) -> Unit> {

    // TODO 3: observable operations for removing and replacing pairs

    // for classifying modification events
    enum class EventType {
        ADD,
        REMOVE, // TODO
        REPLACE  // TODO
    }

    private val data = mutableSetOf<IntPair>()

    init {
        pairs.forEach {
            data.add(it)
        }
    }

    override fun iterator(): Iterator<Pair<Int, Int>> = data.iterator()

    override val observers: MutableList<(EventType, IntPair, IntPair?) -> Unit> = mutableListOf()

    fun add(p: IntPair) {
        if (data.add(p))
        // fires event to registered observers
            notifyObservers {
                it(EventType.ADD, p, null)
            }
    }

    fun remove(p: IntPair) {
        if (data.remove(p))
        // fires event to registered observers
            notifyObservers {
                it(EventType.REMOVE, p, null)
            }
    }

    fun replace(oldPair: IntPair, newPair: IntPair) {
        if (data.remove(oldPair))
        // fires event to registered observers
            if (data.add(newPair)) {
                notifyObservers {
                    it(EventType.REPLACE, newPair, oldPair)
                }
            }
    }

    override fun toString(): String {
        return data.joinToString(separator = "    ") { it.toString() }
    }
}
