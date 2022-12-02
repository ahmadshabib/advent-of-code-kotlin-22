fun main() {

    val input = readInput("Day02")
    var totalScore = 0
    //first round
    input.forEach {
        val moves = it.split(" ")
        totalScore += findIfWin(moves[0], moves[1])
        totalScore += pointsPerSelections(moves[1])

    }
    println(totalScore)

    totalScore = 0
    //second round
    input.forEach {
        val moves = it.split(" ")
        totalScore += findIfWinV2(moves[1])
        totalScore += pointsPerSelectionsV2(moves[0],moves[1])

    }
    println(totalScore)
}

fun pointsPerSelections(elfMove: String): Int {
    return when (elfMove) {
        "X" -> 1
        "Y" -> 2
        "Z" -> 3
        else -> 0
    }
}

fun findIfWin(opponentMove: String, elfMove: String): Int {
    return when (opponentMove) {
       /*Rock*/ "A" -> when (elfMove) {
            "X" -> 3
            "Y" -> 6
            "Z" -> 0
            else -> 0
        }
        /*Paper*/ "B" -> when (elfMove) {
            "X" -> 0
            "Y" -> 3
            "Z" -> 6
            else -> 0
        }
        /*Scissors*/ "C" -> when (elfMove) {
            "X" -> 6
            "Y" -> 0
            "Z" -> 3
            else -> 0
        }
        else -> 0
    }
}

fun findIfWinV2(elfMove: String): Int {
    return when (elfMove) {
        "X" -> 0
        "Y" -> 3
        "Z" -> 6
        else -> 0
    }
}
fun pointsPerSelectionsV2(opponentMove: String, elfMove: String): Int {
    return when (opponentMove) {
        /*Rock*/ "A" -> when (elfMove) {
            "X" -> 3
            "Y" -> 1
            "Z" -> 2
            else -> 0
        }
        /*Paper*/ "B" -> when (elfMove) {
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> 0
        }
        /*Scissors*/ "C" -> when (elfMove) {
            "X" -> 2
            "Y" -> 3
            "Z" -> 1
            else -> 0
        }
        else -> 0
    }
}
