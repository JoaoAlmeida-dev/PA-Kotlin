package Exercise2.fileExercise

import java.io.File

class DirectoryElement(
    val children: MutableList<Element> = mutableListOf<Element>(),
    name: String,
    parent: DirectoryElement?,
    file: File,
) : Element(name, parent, file) {
    init {
        check(file.isDirectory)
        file.listFiles().forEach {
            if (it.isDirectory) {
                children.add(DirectoryElement(name = it.name, parent = this, file = it))
            } else {
                children.add(FileElement(name = it.name, parent = this, file = it))
            }
        }
    }

    val nElements: Int get() = children.size

    override fun prettyPrint() {
        println(tab.repeat(depth) + name)
        children.forEach(action = { element -> element.prettyPrint() })
    }
}