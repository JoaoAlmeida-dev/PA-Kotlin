class Recursion {
    companion object{
        fun sumRangeRec(lowerbound :Int, upperbound : Int) : Int =
            if (lowerbound == upperbound){
                lowerbound
            }else {
                //print("lowerbound: $lowerbound , upperbound $upperbound\n")
                lowerbound +  sumRangeRec(lowerbound + 1, upperbound)
            }
        fun sumRangeRecTail(lowerbound: Int, upperbound: Int) : Int {
            tailrec fun sumRangeRecTail(lowerbound: Int, upperbound: Int, result: Int ): Int {
                val newResult: Int = result + lowerbound
                return if (lowerbound == upperbound) {
                    newResult
                } else {
                    sumRangeRecTail(lowerbound = lowerbound + 1, upperbound = upperbound, result = newResult)
                }
            }
            return sumRangeRecTail(lowerbound, upperbound,0)
        }

        tailrec fun firstDigit(n : Int): Int =
           if (n < 10) {
                n
            } else {
                firstDigit(n / 10)
            }


    }
}