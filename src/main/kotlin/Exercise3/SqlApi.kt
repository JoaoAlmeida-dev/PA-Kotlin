package Exercise3

import Exercise3.SQLMapTypes.MapType
import Exercise3.SQLMapTypes.MysqlMapping
import Exercise3.SQLMapTypes.SqliteMapping
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.isSubclassOf

class SqlApi(val mapping: MapType) {
    companion object {
        fun SqlTest() {
            val sqliteApi: SqlApi = SqlApi(SqliteMapping())
            val mysqlApi: SqlApi = SqlApi(MysqlMapping())

            val clazzPoint: KClass<*> = Point::class
            println("sqlite: ${sqliteApi.createTable(clazzPoint)}")
            println("mysql: ${mysqlApi.createTable(clazzPoint)}")

            val clazzStudent: KClass<*> = Student::class
            println("sqlite: ${sqliteApi.createTable(clazzStudent)}")
            println("mysql: ${mysqlApi.createTable(clazzStudent)}")

            println(sqliteApi.insert(Student(69, "Ronaldo", StudentType.Doctoral)))
        }
    }

    fun createTable(clazz: KClass<*>): String {
        val membersStringList: MutableList<String> = mutableListOf()
        clazz.declaredMemberProperties.forEach { it ->
            //println("${it.name} ${it.returnType} ${it.typeParameters} ")
            val parameterName: String = it.getPropertyName()

            val type: String = mapping.mapType(it.returnType)
            val memberString: String = "$parameterName $type"
            membersStringList.add(memberString)
        }

        val clazzName: String? = clazz.getClassName()
        return "CREATE TABLE $clazzName ( ${membersStringList.joinToString(separator = ", ") { it }} );"
    }


    private fun KClass<*>.getClassName() = if (this.hasAnnotation<DbName>()) {
        this.findAnnotation<DbName>()?.name
    } else {
        this.simpleName
    }

    private fun KProperty1<out Any, *>.getPropertyNullable() =
        if (!this.returnType.isMarkedNullable) {
            "NOT NULL"
        } else {
            "NULL"
        }

    private fun KProperty1<out Any, *>.getPropertyName() =
        if (this.hasAnnotation<DbName>()) {
            this.findAnnotation<DbName>()!!.name
        } else {
            this.name
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