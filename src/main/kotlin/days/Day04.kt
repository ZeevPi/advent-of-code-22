package days

import utils.FileReader

/**
 * Created on 04.12.22
 *
 * @author vpiven
 */
class Day04 : ADay(4) {
    override fun part1(): Number {
        return FileReader.readLinesFromResource(inputFile())
            .asSequence()
            .map { splitToPair(it, ",") }
            .map { Pair(splitToPair(it.first, "-"), splitToPair(it.second, "-")) }
            .map { Pair(rangeToList(it.first), rangeToList(it.second)) }
            .map { it.first.containsAll(it.second) or it.second.containsAll(it.first) }
            .filter { it }
            .count()
    }

    override fun part2(): Number {
        return FileReader.readLinesFromResource(inputFile())
            .asSequence()
            .map { splitToPair(it, ",") }
            .map { Pair(splitToPair(it.first, "-"), splitToPair(it.second, "-")) }
            .map { Pair(rangeToList(it.first), rangeToList(it.second)) }
            .map { it.first intersect it.second.toSet() }
            .filter { it.isNotEmpty() }
            .count()
    }

    private fun splitToPair(str: String, delimiter: String): Pair<String, String> =
        str.split(delimiter).let { Pair(it[0], it[1]) }

    private fun rangeToList(pair: Pair<String, String>): List<Int> =
        (pair.first.toInt() - 1 until pair.second.toInt()).map { it + 1 }
}