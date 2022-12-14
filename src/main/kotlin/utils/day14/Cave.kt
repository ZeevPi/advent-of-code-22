package utils.day14

import java.lang.Integer.max
import java.lang.Integer.min

/**
 * Created on 14.12.22
 *
 * @author vpiven
 */
data class Cave(private val rocks: Set<Position>, private val withFloor: Boolean = false) {

    private val sandUnits = mutableSetOf<Position>()
    private val leftBoundary = rocks.minOf { it.x }
    private val rightBoundary = rocks.maxOf { it.x }
    private val floor = rocks.maxOf { it.y } + 2
    private val sandStartingPoint = Position(500, 0)

    fun simulateFallingSand() {
        var currentPos = sandStartingPoint
        while (!sandUnits.contains(sandStartingPoint)) {
            val nextPos = getNextSandPos(currentPos)
            currentPos = if (nextPos == null || isFloor(nextPos)) {
                sandUnits.add(currentPos)
                sandStartingPoint
            } else if (isAbyss(nextPos)) {
                break
            } else {
                nextPos
            }
        }
    }

    fun countSandUnits(): Int = sandUnits.count()

    private fun isAbyss(pos: Position): Boolean = !withFloor && (pos.x < leftBoundary || rightBoundary < pos.x || floor < pos.y)
    private fun isFloor(pos: Position): Boolean = withFloor && floor == pos.y

    private fun getNextSandPos(current: Position): Position? {
        val y = current.y + 1
        for (x in listOf(current.x, current.x - 1, current.x + 1)) {
            val next = Position(x, y)
            if (isPositionFree(next))
                return next
        }
        return null
    }

    private fun isPositionFree(pos: Position): Boolean = !(rocks.contains(pos) || sandUnits.contains(pos))

    fun print() {
        val minX = min(sandUnits.minOfOrNull { it.x } ?: leftBoundary, leftBoundary) - 1
        val maxX = max(sandUnits.maxOfOrNull { it.x } ?: rightBoundary, rightBoundary) + 1
        (0..floor + 1).forEach() { row ->
            (minX..maxX).map { col ->
                when {
                    withFloor && floor == row -> '#'
                    rocks.contains(Position(col, row)) -> '#'
                    sandUnits.contains(Position(col, row)) -> 'o'
                    else -> '.'
                }
            }.forEach { print(it) }
            println()
        }
    }
}

typealias Position = Pair<Int, Int>

val Position.x: Int get() = this.first
val Position.y: Int get() = this.second