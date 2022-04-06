package Exercise5.Visitors

import Exercise2.fileExercise.DirectoryElement
import Exercise2.fileExercise.FileElement

class TreePrintVisitor : Visitor {
    var treeString: String = ""
    var depth: Int = 0

    override fun visit(e: FileElement) {
        depth++
        treeString += "${"\t".repeat(depth)} ${e.name} \n"
        super.visit(e)
    }

    override fun visit(e: DirectoryElement): Boolean {
        depth++
        return super.visit(e)
    }

    override fun endvisit(e: DirectoryElement) {
        depth--
        super.endvisit(e)
    }
}