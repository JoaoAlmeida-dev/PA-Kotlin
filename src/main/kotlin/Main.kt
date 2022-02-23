fun main(args: Array<String>) {
    recursion()
    lambda()
    higherOrder()
}


private fun recursion() {
    println("----------Recursion----------")
    println("sumRangeRec ${Recursion.sumRangeRec(1, 10_000)}")
    println("sumRangeRecTail ${Recursion.sumRangeRecTail(1, 10_000)}")
    println("firstDigit ${Recursion.firstDigit(51_321_315)}")
}

private fun lambda() {
    val n1: Int = 10
    val n2: Int = 17
    println("----------Lambdas----------")
    println("isEven($n1) = ${Lambda.isEven(n1)}")
    println("isNotEven($n1) = ${Lambda.isNotEven(n1)}")
    println("divisorTen($n1) = ${Lambda.divisorTen(n1)}")
    println("isEven($n2) = ${Lambda.isEven(n2)}")
    println("isNotEven($n2) = ${Lambda.isNotEven(n2)}")
    println("divisorTen($n2) = ${Lambda.divisorTen(n2)}")
}

fun higherOrder() {
    println("----------HigherOrder----------")
    println(HigherOrder.count(1,10,Lambda.isEven))
}
