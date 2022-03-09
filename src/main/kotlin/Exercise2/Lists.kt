package Exercise2

class Lists {
    companion object {
        fun listDemo() {
            val list: List<Int> = List(size = 100) {
                it
            }
            println(list)
        }
    }
}