package days

import utils.FileReader
import utils.day14.Cave
import utils.day14.Position

/**
 * Created on 14.12.22
 *
 * @author vpiven
 */
class Day14 : ADay(14) {

    private val rocks: Set<Position> = FileReader.readLinesFromResource(inputFile())
        .map { parseLine(it) }
        .flatten()
        .toSet()

    override fun part1(): Any {
        val cave = Cave(rocks)
        cave.simulateFallingSand()
        return cave.countSandUnits()
    }

    override fun part2(): Any {
        val cave = Cave(rocks, true)
        cave.simulateFallingSand()
        cave.print()
        return cave.countSandUnits()
    }

    private fun parseLine(line: String): Set<Position> = line.split(" -> ")
        .map { it.split(",") }
        .map { (x, y) -> Position(x.toInt(), y.toInt()) }
        .map { Pair(it, setOf<Position>()) }
        .reduce { acc, pair -> Pair(pair.first, acc.second + getPointsInLine(acc.first, pair.first)) }
        .second

    private fun getPointsInLine(p1: Position, p2: Position): List<Position> =
        getRange(p1.first, p2.first).map { x -> getRange(p1.second, p2.second).map { y -> Pair(x, y) } }.flatten()

    private fun getRange(a: Int, b: Int): IntRange = if (a > b) (b..a) else (a..b)
}