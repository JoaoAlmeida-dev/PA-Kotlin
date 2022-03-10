package exercise1

class FunctionalComposition {
    companion object {
        fun isPerfectNumber(n: Int): Boolean {
            val divisorOfN: (Int) -> Boolean =  divisorOfExceptSelf(n)

            return HigherOrder.sumRangeRecHigher(min = 1, max = n, accept = divisorOfN ) == n
        }

        private fun divisorOfExceptSelf(n:Int): (Int) -> Boolean = { x:Int -> if(x!=n) n%x == 0 else false }

    }
}