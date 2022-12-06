fun main() {

    val input = readInput("Day06")
    input.forEach {
        for (i in 0..it.length - 14) {
            if (it.toCharArray(i, i + 14).toSet().size == 14) {
                println(i + 14)
                break
            }
        }
    }


}
