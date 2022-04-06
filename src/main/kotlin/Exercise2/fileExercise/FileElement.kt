package Exercise2.fileExercise

import Exercise5.Visitors.Visitor
import java.io.File

class FileElement(
    name: String,
    parent: DirectoryElement?,
    file: File,
) : Element(name, parent, file) {
    init {
        check(file.isFile)
    }

    override fun accept(v: Visitor) {
        v.visit(this)
    }

}