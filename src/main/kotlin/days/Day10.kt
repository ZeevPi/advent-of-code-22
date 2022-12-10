package days

import utils.FileReader

/**
 * Created on 10.12.22
 *
 * @author vpiven
 */
class Day10 : ADay(10) {

    private val addx = Regex("addx (-?\\d+)")

    private var cycle = 1
    private var X = 1
    private val registry: MutableMap<Int, Int> = mutableMapOf(cycle to X)

    init {
        FileReader.readLinesFromResource(inputFile())
            .map { parseLine(it) }
            .forEach() { executeCycle(it) }
    }

    override fun part1(): Number {
        return (20..220 step 40)
            .map { Pair(it, registry[it]) }
            .sumOf { it.first * (it.second ?: 0) }
    }

    override fun part2(): String =
        (0..5).joinToString("\n", "\n", "\n") { i ->
            (1..40)
                .map { Pair(it, i * 40 + it) }
                .map {
                    when (it.first - 1) {
                        registry[it.second]!!, registry[it.second]!! - 1, registry[it.second]!! + 1 -> '#'
                        else -> '.'
                    }
                }.joinToString("")
        }

    private fun parseLine(line: String): Command =
        when {
            line == "noop" -> Pair(1, 0)
            addx.matches(line) -> Pair(2, addx.find(line)!!.groupValues[1].toInt())
            else -> Pair(0, 0)
        }

    private fun executeCycle(command: Command) {
        for (i in command.first downTo 1) {
            cycle += 1
            if (i == 1)
                X += command.second
            registry[cycle] = X
        }
    }
}

typealias Command = Pair<Int, Int>