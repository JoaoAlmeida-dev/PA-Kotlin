package Exercise5

import Exercise2.fileExercise.DirectoryElement
import Exercise2.fileExercise.Element
import Exercise2.fileExercise.FileElement
import Exercise5.Visitors.DepthVisitor
import Exercise5.Visitors.FileExtensionCounterVisitor
import Exercise5.Visitors.FileNameVisitor
import Exercise5.Visitors.TreePrintVisitor
import java.io.File

fun Exercise5Demo() {
    println("_\t_Exercise5_\t_")

    val root: File = File(System.getProperty("user.dir"))
    val directoryElement: DirectoryElement = DirectoryElement(name = root.name, file = root)
    directoryElement.prettyPrint()

    //val root2: DirectoryElement = File(System.getProperty("user.dir")).toDirectoryTree()
    //root2.prettyPrint()
    val foundFile: MutableList<Element> = directoryElement.find("Exercise5Demo.kt")
    println(foundFile)

    val fileNameVisitor: FileNameVisitor = FileNameVisitor(name = "DepthVisitor.kt")
    directoryElement.accept(fileNameVisitor)
    println("foundFile:${fileNameVisitor.foundFiles.map { it.name }}")

    val fileExtensionCounterVisitor: FileExtensionCounterVisitor = FileExtensionCounterVisitor(extension = "kt")
    directoryElement.accept(fileExtensionCounterVisitor)
    println("counter:${fileExtensionCounterVisitor.counter}")
    println("foundFiles:${fileExtensionCounterVisitor.foundFiles.map { it.name }}")

    val depthVisitor: DepthVisitor = DepthVisitor()
    directoryElement.accept(depthVisitor)
    println("maxDepth:${depthVisitor.maxDepth}")

    val treePrintVisitor: TreePrintVisitor = TreePrintVisitor()
    directoryElement.accept(treePrintVisitor)
    println("tree:\n${treePrintVisitor.treeString}")


}

fun File.toDirectoryTree(): DirectoryElement {
    return DirectoryElement(name = this.name, file = this)
}

fun Element.find(name: String): MutableList<Element> {
    fun aux(innerName: String, returnList: MutableList<Element>, element: Element) {
        when (element) {
            is FileElement -> {
                if (this.name.contains(innerName)) returnList.add(this)
            }
            is DirectoryElement -> {
                for (child in element.children) {
                    aux(innerName, returnList, child)
                }
            }
        }
    }

    val list: MutableList<Element> = mutableListOf<Element>()
    aux(innerName = name, returnList = list, element = this)
    return list
}


fun DirectoryElement.count() {

}

fun DirectoryElement.maxDepth() {

}




