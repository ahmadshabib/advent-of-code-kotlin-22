fun main() {

    val input = readInput("Day05")
    val stacks = createStacks(numberOfStacks(line = input[0]))
    input.filter { it.contains("[") }.reversed().forEach { fillStack(it, stacks) }
    input
        .asSequence()
        .filter { it.startsWith("move") }
        .map { it.replace("move ", "") }
        .map { it.replace(" from ", ",") }
        .map { it.replace(" to ", ",") }
        .map { it.split(",") }
        .map { Triple(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
        .forEach { moveStacks(it, stacks, Solution.SECOND) }

    stacks.forEach { if (it.isNotEmpty()) print(it.last()) }
}


private fun numberOfStacks(line: String): Int {
    return (line.length + 1) / 4
}

private fun createStacks(numberOfStacks: Int): MutableList<MutableList<String>> {
    val list = mutableListOf<MutableList<String>>()
    for (i in 0..numberOfStacks) list.add(mutableListOf())
    return list
}

private fun fillStack(line: String, stacks: MutableList<MutableList<String>>) {
    stacks.forEachIndexed { index, strings ->
        run {
            val line2 = line.toCharArray()
            if (line2.size > 1 + (index * 4)) {
                val char = line2[1 + (index * 4)]
                if (char.isLetter()) {
                    strings.add(char.toString())
                }
            }
        }
    }
}

private fun moveStacks(
    order: Triple<Int, Int, Int>,
    stacks: MutableList<MutableList<String>>,
    solution: Solution = Solution.FIRST
) {
    var elementsToMove = stacks[order.second - 1].takeLast(order.first)
    when (solution) {
        Solution.FIRST -> elementsToMove = elementsToMove.reversed()
        else -> {}
    }
    stacks[order.third - 1].addAll(elementsToMove)
    stacks[order.second - 1] = stacks[order.second - 1].dropLast(order.first).toMutableList()
}

enum class Solution {
    FIRST, SECOND
}