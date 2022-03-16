package Exercise3

data class Student(
    val number: Int,
    val name: String,
    val type: StudentType? = null,
)

enum class StudentType {
    Bachelor, Master, Doctoral
}