package Exercise2.fileExercise

import java.io.File

class FileElement(
    name: String,
    parent: DirectoryElement?,
    file: File,
) : Element(name, parent, file) {
    init {
        check(file.isFile)
    }

}