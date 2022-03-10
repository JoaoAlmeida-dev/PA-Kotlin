package exercise1

class Recursion {
    companion object {
        fun sumRangeRec(min: Int, max: Int): Int = if (min == max) {
            min
        } else {
            //print("lowerbound: $lowerbound , upperbound $upperbound\n")
            min + sumRangeRec(min + 1, max)
        }

        fun sumRangeRecTail(min: Int, max: Int): Int {
            tailrec fun sumRangeRecTail(min: Int, max: Int, result: Int): Int {
                val newResult: Int = result + min
                return if (min == max) {
                    newResult
                } else {
                    sumRangeRecTail(min = min + 1, max = max, result = newResult)
                }
            }
            return sumRangeRecTail(min, max, 0)
        }



        tailrec fun firstDigit(n: Int): Int = if (n < 10) {
            n
        } else {
            firstDigit(n / 10)
        }


        }
    }