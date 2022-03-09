package Exercise2

import Exercise2.fileExercise.DirectoryElement
import java.io.File

fun Exercise2Demo() {
    Lists.listDemo()
    Files.filesDemo()

    val root: File = File(System.getProperty("user.dir"))
    val directoryElement: DirectoryElement = DirectoryElement(name = root.name, file = root, parent = null)
    directoryElement.prettyPrint()
}