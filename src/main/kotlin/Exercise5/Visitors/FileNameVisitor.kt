package Exercise5.Visitors

import Exercise2.fileExercise.FileElement

class FileNameVisitor(val name: String) : Visitor {
    var foundFiles: MutableList<FileElement> = mutableListOf()

    override fun visit(e: FileElement) {
        if (e.name == this.name) {
            this.foundFiles.add(e)
        }
        super.visit(e)
    }
}