package Exercise5.Visitors

import Exercise2.fileExercise.FileElement

class FileExtensionCounterVisitor(val extension: String) : Visitor {
    var counter: Int = 0
    var foundFiles: MutableList<FileElement> = mutableListOf()


    override fun visit(e: FileElement) {
        if (e.name.split(".").last().equals(extension)) {
            foundFiles.add(e)
            counter++
        }
        super.visit(e)
    }

}