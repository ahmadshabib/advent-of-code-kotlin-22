fun main() {

    val input = readInput("Day08")
    val treesMap = Array(input.size) { Array(input[0].length) { 0 } }
    input.forEachIndexed { index, s ->
        s.toCharArray().forEachIndexed { columnIndex, c ->
            treesMap[index][columnIndex] = c.toString().toInt()
        }
    }
    var numberOfVisible = ((input.size - 1) * 2) + ((input[0].length - 1) * 2)

    //First one
    for (row in 1 until input.size - 1) {
        for (column in 1 until input[0].length - 1) {
            if ((treesMap[row][column] > 0) and (checkVisibleFromRight(column, treesMap[row]) ||
                        checkVisibleFromLeft(column, treesMap[row]) ||
                        checkVisibleFromTop(row, column, treesMap) ||
                        checkVisibleFromBottom(row, column, treesMap))
            ) {
                numberOfVisible++
            }
        }
    }
    // Second One
    var maxTree = 0
    for (row in 1 until input.size - 1) {
        for (column in 1 until input[0].length - 1) {
            val value = maxVisibleFromRight(column, treesMap[row]) * maxVisibleFromBottom(
                row,
                column,
                treesMap
            ) * maxVisibleFromTop(row, column, treesMap) * maxVisibleFromLeft(column, treesMap[row])
            if (value > maxTree) {
                maxTree = value
            }
        }
    }
    println(maxTree)

}

private fun checkVisibleFromRight(column: Int, treesMap: Array<Int>): Boolean {
    val currentTree = treesMap[column]
    val values = treesMap.drop(column + 1)
    val currentMax = values.max()
    if (currentTree > currentMax) {
        return true
    }
    return false
}

private fun checkVisibleFromLeft(column: Int, treesMap: Array<Int>): Boolean {
    val currentTree = treesMap[column]
    val values = treesMap.dropLast(treesMap.size - column)
    val currentMax = values.max()
    if (currentTree > currentMax) {
        return true
    }
    return false
}

private fun checkVisibleFromTop(raw: Int, column: Int, treesMap: Array<Array<Int>>): Boolean {
    val currentTree = treesMap[raw][column]
    val values = treesMap.map { it[column] }.dropLast(treesMap.size - raw)
    val currentMax = values.max()
    if (currentTree > currentMax) {
        return true
    }
    return false
}

private fun checkVisibleFromBottom(raw: Int, column: Int, treesMap: Array<Array<Int>>): Boolean {
    val currentTree = treesMap[raw][column]
    val values = treesMap.map { it[column] }.drop(raw + 1)
    val currentMax = values.max()
    if (currentTree > currentMax) {
        return true
    }
    return false
}

private fun maxVisibleFromRight(column: Int, treesMap: Array<Int>): Int {
    val currentTree = treesMap[column]
    val values = treesMap.drop(column + 1)
    var distance = 0
    for (i in values) {
        if (currentTree > i) {
            distance++
        } else {
            distance++
            break
        }
    }
    return distance
}

private fun maxVisibleFromLeft(column: Int, treesMap: Array<Int>): Int {
    val currentTree = treesMap[column]
    val values = treesMap.dropLast(treesMap.size - column)
    var distance = 0
    for (i in values.size - 1 downTo 0) {
        if (currentTree > values[i]) {
            distance++
        } else {
            distance++
            break
        }
    }
    return distance
}

private fun maxVisibleFromTop(raw: Int, column: Int, treesMap: Array<Array<Int>>): Int {
    val currentTree = treesMap[raw][column]
    val values = treesMap.map { it[column] }.dropLast(treesMap.size - raw)
    var distance = 0
    for (i in values.size - 1 downTo 0) {
        if (currentTree > values[i]) {
            distance++
        } else {
            distance++
            break
        }
    }
    return distance
}

private fun maxVisibleFromBottom(raw: Int, column: Int, treesMap: Array<Array<Int>>): Int {
    val currentTree = treesMap[raw][column]
    val values = treesMap.map { it[column] }.drop(raw + 1)
    var distance = 0
    for (i in values) {
        if (currentTree > i) {
            distance++
        } else {
            distance++
            break
        }
    }
    return distance
}

