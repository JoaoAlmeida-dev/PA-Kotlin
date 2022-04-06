package Exercise2.fileExercise

import Exercise5.Visitors.Visitor
import java.io.File

abstract class Element(
    val name: String,
    val parent: Exercise2.fileExercise.DirectoryElement?,
    val file: File,
) {
    init {
        parent?.children?.add(element = this)
    }

    companion object {
        val tab: String = ".\t"
    }

    open fun prettyPrint() {
        println(tab.repeat(depth) + name)
    }

    override fun toString(): String {
        return "Element(name='$name', file=$file, depth=$depth, path='$path')"
    }

    val depth: Int
        get() = (parent?.depth?.plus(1) ?: 0)
    val path: String get() = (parent?.path + "/" + this.name)

    /**
     * Function for Exercise5
     */
    abstract fun accept(v: Visitor)
}