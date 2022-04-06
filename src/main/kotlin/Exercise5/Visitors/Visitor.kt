package Exercise5.Visitors

import Exercise2.fileExercise.DirectoryElement
import Exercise2.fileExercise.Element
import Exercise2.fileExercise.FileElement

interface Visitor {

    fun visit(e: FileElement) {}
    fun visit(e: DirectoryElement): Boolean = true
    fun endvisit(e: DirectoryElement) {}
}