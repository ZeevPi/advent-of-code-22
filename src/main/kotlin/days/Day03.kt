package days

import utils.FileReader

/**
 * Created on 04.12.22
 *
 * @author vpiven
 */
class Day03 : ADay(3) {
    override fun part1(): Number {
        return FileReader.readLinesFromResource(inputFile())
            .asSequence()
            .map { Pair(it.substring(0, it.length / 2), it.substring(it.length / 2)) }
            .map { Pair(it.first.toSet(), it.second.toSet()) }
            .map { it.first.intersect(it.second) }
            .map { charToPriority(it.elementAtOrNull(0)) }
            .sum()
    }

    override fun part2(): Number {
        return FileReader.readLinesFromResource(inputFile())
            .asSequence()
            .chunked(3)
            .map { it.map { str -> str.toSet() }}
            .map { it.elementAt(0) intersect it.elementAt(1) intersect it.elementAt(2) }
            .map { charToPriority(it.elementAtOrNull(0)) }
            .sum()
    }

    private fun charToPriority(char: Char?): Int =
        if (char != null) (char.code - if (char.isUpperCase()) 38 else 96) else 0
}