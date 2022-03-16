package Exercise3

import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.isSubclassOf

class SQLAPI {
    companion object {
        fun SqlTest() {
            val clazzPoint: KClass<*> = Point::class
            println(createTable(clazzPoint))
            val clazzStudent: KClass<*> = Student::class
            println(createTable(clazzStudent))
            println(insert(Student(69, "Ronaldo", StudentType.Doctoral)))
        }

        fun createTable(clazz: KClass<*>): String {
            val membersStringList: MutableList<String> = mutableListOf()
            clazz.declaredMemberProperties.forEach { it ->
                //println("${it.name} ${it.returnType} ${it.typeParameters} ")
                val nullable: String = if (!it.returnType.isMarkedNullable) {
                    "NOT NULL"
                } else {
                    "NULL"
                }
                val atributeClassifier = it.returnType.classifier
                val type: String = when (atributeClassifier) {
                    Int::class -> "INT"
                    String::class -> "VARCHAR(255)"
                    Boolean::class -> "BOOLEAN"
                    else -> {
                        val classOfAtribute = atributeClassifier as KClass<*>
                        if (classOfAtribute.isSubclassOf(Enum::class)) {
                            "ENUM( ${classOfAtribute.java.enumConstants.joinToString(separator = ", ")} )"
                        } else {
                            "VARCHAR(255)"
                        }
                    }
                }
                val memberString: kotlin.String = "${it.name} $type $nullable"
                membersStringList.add(memberString)
            }

            return "CREATE TABLE ${clazz.simpleName} ( ${membersStringList.joinToString(separator = ", ") { it }} );"
        }

        fun insert(obj: Any): String {
            val membersStringList: MutableList<String> = mutableListOf()
            val valuesStringList: MutableList<String> = mutableListOf()
            val clazz = obj::class
            clazz.declaredMemberProperties.forEach {
                membersStringList.add(it.name)

                val element = when (it.returnType.classifier) {
                    Int::class -> it.call(obj).toString()
                    else -> "\'${it.call(obj).toString()}\'"
                }
                valuesStringList.add(element)
            }

            return "INSERT INTO ${clazz.simpleName} ( ${membersStringList.joinToString(separator = ", ")} ) VALUES ( ${
                valuesStringList.joinToString(separator = ", ")
            } );"
        }
    }
}