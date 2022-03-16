package Exercise3

import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

class Point(val x: Int, val y: Int) {
    constructor() : this(0, 0)

    val isOrigin: Boolean get() = x == 0 && y == 0

    fun moveDown() = Point(x, y - 1)
    fun moveUp() = Point(x, y + 1)

    fun moveLeft() = Point(x - 1, y)
    fun moveRigth() = Point(x + 1, y)

    fun sum(x: Int, y: Int) = Point(this.x + x, this.y + y)
}

fun main() {
    val clazz: KClass<*> = Point()::class
    println(clazz.qualifiedName)
    clazz.declaredMemberProperties.forEach {
        println(it)
    }
}