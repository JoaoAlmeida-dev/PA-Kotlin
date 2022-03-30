package Exercise3.SQLMapTypes

import Exercise3.Length
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.test.fail

class MysqlMapping : MapType {

    /*
    override val Int: String
           get() = "INT"
    override val String: String
        get() = "VARCHAR(255)"
    override val Boolean: String
        get() = "BOOLEAN"
    override val Double: String
        get() = "DOUBLE"
     */

    override fun mapType(c: KType): String {
        return if (c.classifier.isEnum())
            "ENUM(" + (c.classifier as KClass<*>).enumConstants().joinToString { "'$it'" } + ")"
        else {
            when (c.classifier) {
                String::class -> {
                    val hasAnnotation: Boolean = c.hasAnnotation<Length>()
                    if (hasAnnotation) {
                        "VARCHAR(${c.findAnnotation<Length>()?.length})"
                    } else {
                        "VARCHAR(255)"
                    }
                }
                Int::class -> "INT"
                Double::class -> "DOUBLE"
                Boolean::class -> "BOOLEAN"
                else -> fail("not supported")
            }

        }
    }

    override fun mapObject(o: Any?): String =
        if (o == null) "NULL"
        else if (o is String || o::class.isEnum()) "'${o}'"
        else o.toString()

}