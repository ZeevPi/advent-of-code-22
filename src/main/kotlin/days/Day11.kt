package days

import utils.day11.Monkey
import utils.FileReader

import kotlin.math.floor
import kotlin.math.roundToInt

/**
 * Created on 11.12.22
 *
 * @author vpiven
 */
class Day11 : ADay(11) {
    override fun part1(): Number {
        val monkeyByName = FileReader.readLinesFromResource(inputFile())
            .filter { it.isNotBlank() }
            .chunked(6) {
                Monkey.parseMonkey(it)
            }.associateBy { it.name }

        (1..20).forEach { _ ->
            simulateRound(monkeyByName)
            println(monkeyByName.values)
        }

        return monkeyByName.values
            .map { it.inspectedItems }
            .sortedDescending()
            .slice(0..1)
            .reduce { acc, i -> acc * i }
    }

    override fun part2(): Any {
        TODO()
    }

    private fun simulateRound(monkeys: Map<Int, Monkey>) {
        (0 until monkeys.size).forEach {
            val monkey = monkeys[it]
            while (monkey!!.hasItems()) {
                val item = monkey.inspectItem { i -> floor(i / 3.0).roundToInt() }
                monkeys[monkey.test(item)]!!.receiveItem(item)
            }
        }
    }
}