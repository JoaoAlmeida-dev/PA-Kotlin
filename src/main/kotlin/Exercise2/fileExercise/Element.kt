package Exercise2.fileExercise

import java.io.File

abstract class Element(val name: String, val parent: DirectoryElement?, val file: File) {
    init {
        parent?.children?.add(element = this)
    }

    companion object {
        val tab: String = ".\t"
    }

    open fun prettyPrint() {
        println(tab.repeat(depth) + name)
    }

    val depth: Int
        get() = (parent?.depth?.plus(1) ?: 0)
    val path: String get() = (parent?.path + "/" + this.name)
}