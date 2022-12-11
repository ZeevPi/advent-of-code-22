package days

import utils.FileReader
import utils.day11.Monkey
import kotlin.math.floor
import kotlin.math.roundToInt

/**
 * Created on 11.12.22
 *
 * @author vpiven
 */
class Day11 : ADay(11) {
    override fun part1(): Number {
        val monkeyByName = getMonkeys()

        (1..20).forEach { _ ->
            simulateRound(monkeyByName) { x -> floor(x / 3.0).roundToInt() }
        }

        return getMonkeyBusinessLevel(monkeyByName)
    }

    override fun part2(): Any {
        val monkeyByName = getMonkeys()

        (1..10000).forEach { _ ->
            simulateRound(monkeyByName)
        }

        return getMonkeyBusinessLevel(monkeyByName)
    }

    private fun getMonkeys(): Map<Int, Monkey> {
        val monkeyByName = FileReader.readLinesFromResource(inputFile())
            .filter { it.isNotBlank() }
            .chunked(6) {
                Monkey.parseMonkey(it)
            }.associateBy { it.name }
        return monkeyByName
    }

    private fun getMonkeyBusinessLevel(monkeys: Map<Int, Monkey>): Long = monkeys.values
        .map { it.inspectedItems }
        .sortedDescending()
        .slice(0..1)
        .fold(1L) { acc, i -> acc * i }

    private fun simulateRound(monkeys: Map<Int, Monkey>, worryLevelReducer: ((Int) -> Int)? = null) {
        (0 until monkeys.size).forEach {
            val monkey = monkeys[it]
            while (monkey!!.hasItems()) {
                val item = monkey.inspectItem()
                monkeys[monkey.test(item, worryLevelReducer)]!!.receiveItem(item)
            }
        }
    }
}