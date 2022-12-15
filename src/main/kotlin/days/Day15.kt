package days

import utils.FileReader
import utils.Point2D
import kotlin.math.abs

/**
 * Created on 15.12.22
 *
 * @author vpiven
 */
class Day15 : ADay(15) {

    private val regex = Regex("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)")
    private val elements: List<Pair<Point2D, Point2D>> = FileReader.readLinesFromResource(inputFile())
        .asSequence()
        .filter { regex.matches(it) }
        .map { regex.find(it)!!.groupValues }
        .map { (_, sx, sy, bx, by) -> Pair(Point2D(sx.toInt(), sy.toInt()), Point2D(bx.toInt(), by.toInt())) }
        .toList()
    private val beacons: Set<Point2D> = elements.map { it.second }.toSet()

    private val y = 2_000_000
    private val maxRange = 4_000_000

    override fun part1(): Int {
        val ranges = getRangesForY(y)
        return (ranges.minOf { it.first }..ranges.maxOf { it.last }).size() - beacons.filter { it.y == y }.size
    }

    override fun part2(): Long {
        (0..maxRange).forEach { y ->
            getRangesForY(y).reduce { acc, next ->
                (acc union next) ?: return (acc.last + 1) * 4_000_000L + y
            }
        }

        throw Exception("Beacon not found")
    }

    private fun getRangesForY(y: Int) =
        elements.mapNotNull { (sensor, beacon) -> getXs(sensor, beacon, y) }.sortedBy { it.first }

    private fun getXs(sensor: Point2D, beacon: Point2D, y: Int): IntRange? {
        val radius = sensor manhattanDistanceTo beacon
        val offset = abs(sensor.y - y)
        return (sensor.x - radius + offset..sensor.x + radius - offset).takeUnless { it.isEmpty() }
    }

    private fun IntRange.size() = last - first + 1

    private infix fun IntRange.union(other: IntRange): IntRange? =
        if (this.first <= other.last && this.last >= other.first) {
            IntRange(minOf(first, other.first), maxOf(last, other.last))
        } else null

}