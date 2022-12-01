fun main() {

    val input = readInput("Day01")
    val elfs = mutableListOf<Long>()
    var temp = 0L
    input.forEach {
        if (it.isNotEmpty()) {
            temp += it.toLong()
        } else {
            elfs.add(temp)
            temp = 0L
        }
    }
    println(elfs)
    // first part
    println(elfs.max())
    // second part
    elfs.sortDescending()
    println(elfs.subList(0,3).sum())
}
