package Exercise3

import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

fun Exercise3Demo() {
    println("_\t_Exercise3_\t_")

    val clazz: KClass<*> = Point()::class
    println(clazz.qualifiedName)
    clazz.declaredMemberProperties.forEach {
        println(it)
    }

    SqlApi.SqlTest()
}
