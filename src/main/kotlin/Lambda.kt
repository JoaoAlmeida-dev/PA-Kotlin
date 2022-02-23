
class Lambda {
    companion object{
        val isEven = {
                n:Int -> n%2 == 0
        }
        val isNotEven = {
            n:Int -> !isEven(n)
        }
        val divisorTen = {
            n:Int -> n%10 == 0
        }
    }
}