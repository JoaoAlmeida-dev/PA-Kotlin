package Exercise5.Visitors

import Exercise2.fileExercise.DirectoryElement
import Exercise2.fileExercise.FileElement
import kotlin.math.max

class DepthVisitor : Visitor {
    var depth = 0
    var maxDepth = 0
    override fun visit(e: FileElement) {
        depth++
        if (depth > maxDepth)
            maxDepth = depth
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