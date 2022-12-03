fun main() {

    val input = readInput("Day03_2")
    var values = 0
    //first round
    input.forEach {
        val firstPart = it.substring(0, it.length / 2).toCharArray()
        val secondPart = it.substring(it.length / 2).toList()
        for (charachter in firstPart) {
            if (secondPart.contains(charachter)) { // Tried binary search but didn't work, need to submit a bug to kotlin
                val charval = (if (charachter.isUpperCase()) 26 else 0) + (charachter.toLowerCase() - 'a' + 1)
                values += charval
                break
            }
        }
    }

    //second round
    input.chunked(3).forEach {
        for (charachter in it[0]) {
            if (it[1].contains(charachter) && it[2].contains(charachter)) { // Tried binary search but didn't work, need to submit a bug to kotlin
                val charval = (if (charachter.isUpperCase()) 26 else 0) + (charachter.toLowerCase() - 'a' + 1)
                values += charval
                break
            }
        }
    }
    println(values)
}