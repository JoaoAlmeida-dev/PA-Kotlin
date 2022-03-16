package Exercise2

import Exercise2.fileExercise.DirectoryElement
import java.io.File

fun Exercise2Demo() {
    println("_\t_Exercise2_\t_")
    Lists.listDemo()
    Files.filesDemo()

    //val root: File = File(System.getProperty("user.dir"))
    //val directoryElement: DirectoryElement = DirectoryElement(name = root.name, file = root)
    //directoryElement.prettyPrint()

    val root2: DirectoryElement = File(System.getProperty("user.dir")).toDirectoryTree()
    root2.prettyPrint()
}

fun File.toDirectoryTree(): DirectoryElement {
    return DirectoryElement(name = this.name, file = this)
}