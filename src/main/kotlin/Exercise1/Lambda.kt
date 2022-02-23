package Exercise1
class Lambda {
    companion object{
        val isEven : (Int)-> Boolean = { n:Int ->n%2==0}
        fun isEven(n:Int):Boolean  {
                return n%2 == 0
        }
        fun isNotEven(n:Int):Boolean {
            return !isEven(n)
        }
        fun divisorTen(n:Int):Boolean {
            return n%10 == 0
        }

        fun divisorOf(n:Int): (Int) -> Boolean = { x:Int -> n%x == 0  }
    }
}