package Exercise6.controller

import Exercise6.model.IntPair
import Exercise6.model.PairDataSet

class Controller(
    val model: PairDataSet,
) {
    fun replacePoint(newPoint: IntPair, oldPoint: IntPair) {
        model.remove(oldPoint)
        model.add(newPoint)
    }

    fun remPoint(point: IntPair) {
        model.remove(point)
    }

    fun addPoint(point: IntPair) {
        model.add(point)
    }

    fun undo() {
        TODO("Not yet implemented")
    }


}