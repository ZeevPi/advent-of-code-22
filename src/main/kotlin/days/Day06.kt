package days

import utils.FileReader

/**
 * Created on 06.12.22
 *
 * @author vpiven
 */
class Day06 : ADay(6) {

    override fun part1(): Number {
        val input: String = FileReader.readLinesFromResource(inputFile()).first()
        return findMarkerPos(input, 4)
    }

    override fun part2(): Number {
        val input: String = FileReader.readLinesFromResource(inputFile()).first()
        return findMarkerPos(input, 14)
    }

    private fun findMarkerPos(input: String, length: Int): Int {
        val lastSeenChars: ArrayDeque<Char> = ArrayDeque()
        for ((i, c) in input.withIndex()) {
            lastSeenChars.addLast(c)
            if (lastSeenChars.size > length)
                lastSeenChars.removeFirst()
            if (lastSeenChars.toSet().size == length)
                return i + 1
        }
        return input.length
    }
}