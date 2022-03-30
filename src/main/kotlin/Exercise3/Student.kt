package Exercise3

@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
annotation class DbName(val name: String)

@Target(AnnotationTarget.PROPERTY)
annotation class PrimaryKey

@Target(AnnotationTarget.PROPERTY)
annotation class Length(val length: Int)

@DbName("STUDENT")
data class Student(
    @PrimaryKey
    val number: Int,
    @Length(50)
    val name: String,
    @DbName("degree")
    val type: StudentType? = null,
)

enum class StudentType {
    Bachelor, Master, Doctoral
}


/*
@DbName("STUDENT")
data class Student(
    @PrimaryKey
    val number: Int,
    @Length(50)
    val name: String,
    @DbName("degree")
    val type: StudentType
)
 */