package days

import utils.FileReader

/**
 * Created on 07.12.22
 *
 * @author vpiven
 */
class Day07 : ADay(7) {

    private val filePattern: Regex = Regex("(\\d+) (.+)")
    private val dirPattern: Regex = Regex("dir (\\w+)")
    private val changeDirPattern: Regex = Regex("\\$ cd (\\w+)")

    private val root: Dir = Dir("/", null)
    private var currentDir: Dir = root

    init {
        FileReader.readLinesFromResource(inputFile()).forEach { parseLine(it) }
    }

    override fun part1(): Number {
        val dirsWithSizeBelowLimit = mutableListOf<Dir>()
        addDirsLowerThanLimit(root, 100000, dirsWithSizeBelowLimit)

        return dirsWithSizeBelowLimit.sumOf { it.size() }
    }

    override fun part2(): Number {
        val neededSpace = 30000000 - (70000000 - root.size())
        val dirsWithSizeLargerLimit = mutableListOf<Dir>()
        addDirsLargerThanLimit(root, neededSpace, dirsWithSizeLargerLimit)

        return dirsWithSizeLargerLimit.map { it.size() }.minOf { it }
    }

    private fun parseLine(line: String) {
        when {
            // Change dir to root
            Regex("\\$ cd /").matches(line) -> currentDir = root
            // Go one directory up
            Regex("\\$ cd ..").matches(line) -> {
                if (currentDir.parent != null)
                    currentDir = currentDir.parent!!
                else
                    throw Exception("You are going out of root!")
            }

            // Change dir to ...
            changeDirPattern.matches(line) -> {
                val dirName = changeDirPattern.find(line)!!.groupValues[1]
                val subDir = currentDir.getDirByName(dirName)
                if (subDir != null)
                    currentDir = subDir
                else
                    throw Exception("Desired dir '$dirName' is not a child of '${currentDir.name}'")
            }

            // Add a directory to the current one as a child
            dirPattern.matches(line) -> Dir(dirPattern.find(line)!!.groupValues[1], currentDir)
            // Add a file to the current directory
            filePattern.matches(line) -> {
                val groupValues = filePattern.find(line)!!.groupValues
                currentDir.add(File(groupValues[2], groupValues[1].toInt()))
            }

            // In case of "$ ls" do nothing
            else -> Unit
        }
    }

    private fun addDirsLowerThanLimit(dir: Dir, limit: Int, list: MutableList<Dir>) {
        if (dir.size() < limit)
            list.add(dir)
        dir.children.filterIsInstance<Dir>().forEach { addDirsLowerThanLimit(it, limit, list) }
    }

    private fun addDirsLargerThanLimit(dir: Dir, limit: Int, list: MutableList<Dir>) {
        if (dir.size() > limit)
            list.add(dir)
        dir.children.filterIsInstance<Dir>().forEach { addDirsLargerThanLimit(it, limit, list) }
    }

    /**
     * Represents a file system element
     */
    interface FileSystemElem {
        fun size(): Int
        fun toPrettyString(prefix: String): String
    }

    abstract class AFileSystemElem(val name: String, private val type: String) : FileSystemElem {
        override fun toString(): String {
            return "$name ($type, size=${size()})"
        }
    }

    /**
     * Represents a directory
     */
    class Dir(
        name: String,
        val parent: Dir?,
        val children: MutableList<FileSystemElem> = mutableListOf()
    ) : AFileSystemElem(name, "dir") {

        init {
            parent?.children?.add(this)
        }

        fun add(child: FileSystemElem) {
            children.add(child)
        }

        fun getDirByName(dirName: String): Dir? = children
            .asSequence()
            .filterIsInstance<Dir>()
            .filter { it.name == dirName }
            .firstOrNull()

        override fun size(): Int {
            return children.asSequence()
                .map { it.size() }
                .sum()
        }

        override fun toPrettyString(prefix: String): String {
            val builder = StringBuilder()
            builder.append("$prefix- ${toString()}\n")
            children
                .map { it.toPrettyString("$prefix  ") }
                .forEach { builder.append(it) }
            return builder.toString()
        }
    }

    /**
     * Represents a file
     */
    class File(name: String, private val size: Int) : AFileSystemElem(name, "file") {
        override fun size(): Int = size

        override fun toPrettyString(prefix: String): String = "$prefix- ${toString()}\n"
    }
}