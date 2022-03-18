package Test

import java.util.*
import kotlin.collections.HashMap

class Anagram {
    companion object{
        fun removeAnagrams(anagrams: Array<String>): MutableList<String> {

            fun createMapFromWord(word:String):java.util.HashMap<Char, Int>{
                val hashMap: java.util.HashMap<Char, Int> = HashMap<Char, Int>()
                word.forEach {entry: Char ->
                    hashMap[entry] = hashMap.getOrDefault(entry,0) +1
                }
                return hashMap
            }

            val hashMap: java.util.HashMap<String, java.util.HashMap<Char, Int>> = HashMap<String, HashMap<Char, Int>>()

            anagrams.forEach {entry: String ->
                hashMap[entry] = createMapFromWord(entry)
            }
            val newArray: MutableList<String> = mutableListOf<String>()
            anagrams.forEachIndexed { index: Int, word: String ->
            var hasAnagram : Boolean = false
                for (i in 0..index){
                    //println("${word}:${hashMap[word]} , ${anagrams[i]}:${hashMap[anagrams[i]]} , ${hashMap[word] == hashMap[anagrams[i]]}")
                    if(hashMap[word] == hashMap[anagrams[i]] && word != anagrams[i]){
                        hasAnagram = true
                    }
                }
                if (!hasAnagram){
                    newArray.add(word)
                }
            }

            println(hashMap)
            return newArray
        }
    }
}

fun main() {
    val anagrams : Array<String> = arrayOf("code", "doce", "ecod", "framer", "frame", "farmer", "frrmer")
    val startTime: Long = System.currentTimeMillis()
    val newArray: MutableList<String> = Anagram.removeAnagrams(anagrams)

    println(anagrams.contentToString())
    println(newArray)
    val endTime: Long = System.currentTimeMillis()
    val elapsedTime: Long = (endTime - startTime)
    println("Milliseconds elapsed: $elapsedTime")
}