package days

import utils.FileReader

/**
 * Created on 01.12.22
 *
 * @author vpiven
 */
class Day01 : ADay(1) {
    override fun part1(): Number {
        return getSortedCalories().first()
    }

    override fun part2(): Number {
        return getSortedCalories().take(3).sum()
    }

    private fun getSortedCalories(): List<Long> = FileReader.readLinesFromResource(inputFile())
        .map { it.toLongOrNull() }
        .fold(mutableListOf<MutableList<Long>>(mutableListOf())) { acc, next -> addValueToList(next, acc) }
        .map { it.sum() }
        .sortedDescending()

    private fun addValueToList(value: Long?, list: MutableList<MutableList<Long>>): MutableList<MutableList<Long>> {
        if (value == null) {
            list.add(mutableListOf())
        } else {
            list.last().add(value)
        }
        return list
    }
}