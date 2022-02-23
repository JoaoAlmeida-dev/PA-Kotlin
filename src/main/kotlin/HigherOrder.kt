class HigherOrder {
    companion object{
        fun count(min:Int, max:Int, accept: (Int)-> Boolean  = { n: Int -> true }):Int =
            if ( min > max) 0
            else if (accept(min)) 1 + count(min + 1 , max, accept)
            else count(min + 1 , max , accept)
    }
}