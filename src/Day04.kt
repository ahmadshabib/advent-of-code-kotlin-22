fun main() {

    val input = readInput("Day04")

    println(firstSolution(input))
    println(secondSolution(input))
}

fun firstSolution(input: List<String>): Int =
    input
        .map {
            converty(it)
        }.filter {
            (it.first.first <= it.second.first && it.first.second >= it.second.second) ||
                    (it.first.first >= it.second.first && it.first.second <= it.second.second)

        }.size

fun secondSolution(input: List<String>): Int =
    input.map { converty(it) }
        .map { Pair(IntRange(it.first.first, it.first.second), IntRange(it.second.first, it.second.second)) }
        .filter { (it.first intersect it.second).isNotEmpty() }
        .size

fun converty(inputLine: String): Pair<Pair<Int, Int>, Pair<Int, Int>> {
    val array = inputLine.split(",").flatMap { it.split("-") }
    return Pair(Pair(array[0].toInt(), array[1].toInt()), Pair(array[2].toInt(), array[3].toInt()))
}