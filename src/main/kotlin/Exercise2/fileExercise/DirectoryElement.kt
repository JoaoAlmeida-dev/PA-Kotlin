package Exercise2.fileExercise

import Exercise5.Visitors.Visitor
import java.io.File

class DirectoryElement(
    val children: MutableList<Element> = mutableListOf<Element>(),
    name: String,
    parent: DirectoryElement? = null,
    file: File,
) : Element(name, parent, file) {
    init {
        check(file.isDirectory)
        file.listFiles().forEach {
            if (it.isDirectory) {
                DirectoryElement(name = it.name, parent = this, file = it)
            } else {
                FileElement(name = it.name, parent = this, file = it)
            }
        }
    }

    val nElements: Int get() = children.size

    override fun prettyPrint() {
        println(tab.repeat(depth) + name)
        children.forEach(action = { element -> element.prettyPrint() })
    }

    override fun accept(v: Visitor) {
        if (v.visit(this)) {
            children.forEach {
                it.accept(v)
            }
        }
        v.endvisit(this)
    }


}