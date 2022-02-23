package exercise1


fun main() {
    Exercise1Demo()
}

fun Exercise1Demo() {
    recursion()
    lambda()
    higherOrder()
    functionalComposition()
}
val name:String = "exercise1"

private fun recursion() {
    println("----------$name.Recursion----------")
    println("sumRangeRec ${Recursion.sumRangeRec(1, 10_000)}")
    println("sumRangeRecTail ${Recursion.sumRangeRecTail(1, 10_000)}")
    println("firstDigit ${Recursion.firstDigit(51_321_315)}")
}

private fun lambda() {
    val n1: Int = 10
    val n2: Int = 17
    println("----------$name.Lambdas----------")
    println("isEven($n1) = ${Lambda.isEven(n1)}")
    println("isNotEven($n1) = ${Lambda.isNotEven(n1)}")
    println("divisorTen($n1) = ${Lambda.divisorTen(n1)}")
    println("isEven($n2) = ${Lambda.isEven(n2)}")
    println("isNotEven($n2) = ${Lambda.isNotEven(n2)}")
    println("divisorTen($n2) = ${Lambda.divisorTen(n2)}")
    (1..10)
        .filter { i -> Lambda.isEven(i) }
        .forEach { print("$it ") }
    println()
}

fun higherOrder() {
    println("----------$name.HigherOrder----------")
    println(HigherOrder.count(1, 20, Lambda.Companion::isEven))
    println(HigherOrder.sumRangeRecHigher(1, 20, Lambda.Companion::divisorTen))
    println(HigherOrder.sumRangeRecHigher(1, 20, Lambda.Companion::isEven))
}

fun functionalComposition(){
    println("----------$name.FunctionalComposition----------")
    (1..10000)
        .filter { n -> FunctionalComposition.isPerfectNumber(n) }
        .forEach { n -> println(n) }  // 6, 28, 496, 8128
}