package Exercise3.SQLMapTypes

import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlin.test.fail

class SqliteMapping : MapType {

    /*
     override val Int: String
        get() = "INTEGER"
    override val String: String
        get() = "TEXT"
    override val Boolean: String
        get() = "BOOL"
    override val Double: String
        get() = "REAL"
     */
    override fun mapType(c: KType): String {
        return (if (c.classifier.isEnum())
            "ENUM(" + (c.classifier as KClass<*>).enumConstants().joinToString { "'$it'" } + ")"
        else {
            when (c.classifier) {
                String::class -> "TEXT"
                Int::class -> "INTEGER"
                Double::class -> "REAL"
                Boolean::class -> "BOOL"
                else -> fail("not supported")
            }

        }
                ) + (if (c.isMarkedNullable) "" else " NOT NULL")
    }

    override fun mapObject(o: Any?): String =
        if (o == null) "NULL"
        else if (o is String || o::class.isEnum()) "'${o}'"
        else o.toString()

}