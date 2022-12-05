package days

import utils.FileReader

/**
 * Created on 05.12.22
 *
 * @author vpiven
 */
class Day05 : ADay(5) {

    private val stackPattern: String = "(\\s{3}|\\[\\w])\\s?"
    private val stackNumberPattern: String = "(\\s\\d\\s)\\s?"
    private val movePattern: Regex = Regex("move (\\d+) from (\\d+) to (\\d+)")

    override fun part1(): String {
        val lines = FileReader.readLinesFromResource(inputFile())
        val numberOfStacks: Int = parseNumberOfStacks(lines)
        val stacks: Map<Int, ArrayDeque<Char>> = parseStacks(numberOfStacks, lines)
        parseMoves(lines)
            .forEach { (n, start, end) ->
                (1..n).forEach { _ -> stacks[end]!!.addFirst(stacks[start]!!.removeFirst()) }
            }

        return getCratesOrder(stacks)
    }

    override fun part2(): String {
        val lines = FileReader.readLinesFromResource(inputFile())
        val numberOfStacks: Int = parseNumberOfStacks(lines)
        val stacks: Map<Int, ArrayDeque<Char>> = parseStacks(numberOfStacks, lines)
        parseMoves(lines)
            .forEach { (n, start, end) ->
                (n - 1 downTo 0).forEach { stacks[end]!!.addFirst(stacks[start]!!.removeAt(it)) }
            }

        return getCratesOrder(stacks)
    }

    private fun parseNumberOfStacks(lines: List<String>): Int = lines.asSequence()
        .filter { Regex("(${stackNumberPattern})*").matches(it) }
        .map { Regex(stackNumberPattern).findAll(it) }
        .map { it.toList().size }
        .first()

    private fun parseStacks(numOfStacks: Int, lines: List<String>): Map<Int, ArrayDeque<Char>> {
        val stacks: Map<Int, ArrayDeque<Char>> = (1..numOfStacks).toList().associateWith { ArrayDeque() }
        lines.asSequence()
            .filter { Regex("(${stackPattern})*").matches(it) }
            .map { Regex(stackPattern).findAll(it) }
            .map { it.map { res -> res.groupValues[1] } }
            .forEach { it.forEachIndexed { i: Int, s: String -> if (s.isNotBlank()) stacks[i + 1]!!.addLast(s[1]) } }

        return stacks
    }

    private fun parseMoves(lines: List<String>): Sequence<Triple<Int, Int, Int>> = lines.asSequence()
        .filter { movePattern.matches(it) }
        .map { movePattern.find(it) }
        .map { it!!.destructured }
        .map { (n, start, end) -> Triple(n.toInt(), start.toInt(), end.toInt()) }

    private fun getCratesOrder(stacks: Map<Int, ArrayDeque<Char>>): String = stacks.map { it.value }
        .map { it.first() }
        .joinToString(separator = "")
}