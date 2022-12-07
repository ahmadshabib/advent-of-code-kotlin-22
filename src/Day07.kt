fun main() {

    val input = readInput("Day07")
    var currentDirectory: Directory? = null
    val directories: MutableList<Directory> = mutableListOf()
    for (command in input) {
        if (command.startsWith("\$ cd")) {
            if (command.startsWith("\$ cd ..")) {
                currentDirectory = currentDirectory?.parent
                continue
            }
            if (currentDirectory == null) {
                val dir = Directory(command.replace("\$ cd", ""), null)
                currentDirectory = dir
                directories.add(dir)
            } else {
                val dir = Directory(command.replace("\$ cd", ""), currentDirectory)
                currentDirectory.addEntry(dir)
                directories.add(dir)
                currentDirectory = dir
            }
        } else if (!command.startsWith("\$")) {
            if (command.startsWith("dir")) {
                continue
            } else {
                val split = command.split(" ")
                val file = File(split[1], currentDirectory, split[0].toInt())
                currentDirectory?.addEntry(file)
            }
        }
    }

    // first
    println(directories.filter { it.size() < 100000 }.sumOf { it.size() })
    // second
    val unusedSpace = 70000000 - directories.first { it.name == " /" }.size()
    val spaceNeeded = 30000000 - unusedSpace
    println(directories.filter { it.size() >= spaceNeeded }.minBy { it.size() }.size())


}

abstract class Entry(var name: String, directory: Directory?) {
    var parent: Directory?
    abstract fun size(): Int

    init {
        parent = directory
    }

    override fun toString(): String {
        return "$name - ${size()}"
    }
}

class File(name: String, directory: Directory?, private val size: Int) : Entry(name, directory) {
    override fun size(): Int {
        return size
    }
}


class Directory(name: String, directory: Directory?) : Entry(name, directory) {
    private var contents: MutableList<Entry> = mutableListOf()

    override fun size(): Int {
        var size = 0
        for (e in contents) size += e.size()
        return size
    }

    fun addEntry(entry: Entry) {
        contents.add(entry)
    }

    override fun toString(): String {
        return "$name + ${contents.joinToString { it.toString() }}"
    }

}