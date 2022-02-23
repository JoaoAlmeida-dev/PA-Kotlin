package Exercise1

class HigherOrder {
    companion object{
        fun count(min:Int, max:Int, accept: (Int)-> Boolean  = { _: Int -> true }):Int =
            if ( min > max) 0
            else if (accept(min)) 1 + count(min + 1 , max, accept)
            else count(min + 1 , max , accept)

        fun sumRangeRecHigher(min: Int, max: Int, accept: (Int) -> Boolean): Int =
            if (min == max) if(accept(min)) min else 0
            else if (accept(min)) min + sumRangeRecHigher(min + 1, max, accept)
            else sumRangeRecHigher(min + 1, max, accept)
    }
}