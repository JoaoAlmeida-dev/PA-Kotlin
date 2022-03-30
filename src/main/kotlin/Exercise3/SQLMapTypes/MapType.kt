package Exercise3.SQLMapTypes

import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlin.reflect.full.isSubclassOf

interface MapType {
    fun mapType(c: KType): String
    fun mapObject(o: Any?): String


    // saber se um KClassifier é um enumerado
    public fun KClassifier?.isEnum() = this is KClass<*> && this.isSubclassOf(Enum::class)

    // obter uma lista de constantes de um tipo enumerado
    public fun <T : Any> KClass<T>.enumConstants(): List<T> {
        require(isEnum()) { "class must be enum" }
        return this.java.enumConstants.toList()
    }

}
