package days

import utils.FileReader
import java.util.LinkedList
import kotlin.math.abs

/**
 * Created on 09.12.22
 *
 * @author vpiven
 */
class Day09 : ADay(9) {

    override fun part1(): Number {
        return getVisitedPositionsByTail(createRope(2))
    }

    override fun part2(): Number {
        return getVisitedPositionsByTail(createRope(10))
    }

    private fun createRope(size: Int): Rope = LinkedList((1..size).map { Pair(0, 0) })

    private fun getVisitedPositionsByTail(rope: Rope): Int =
        FileReader.readLinesFromResource(inputFile())
            .asSequence()
            .map { it.split(" ") }
            .map { (direction, moves) ->
                moveHead(rope, direction, moves.toInt())
            }
            .flatten()
            .toSet()
            .size

    private fun moveHead(rope: Rope, direction: String, moves: Int): Set<Knot> {
        return (0 until moves).map {
            val (head, _) = rope
            when (direction) {
                "D" -> rope[0] = Pair(head.first, head.second - 1)
                "U" -> rope[0] = Pair(head.first, head.second + 1)
                "L" -> rope[0] = Pair(head.first - 1, head.second)
                "R" -> rope[0] = Pair(head.first + 1, head.second)
            }
            moveRope(rope, 1)
        }.toSet()
    }

    private fun moveRope(rope: Rope, knot: Int): Knot {
        return if ((knot >= rope.size) || areKnotsConnected(rope[knot - 1], rope[knot])) {
            rope.last
        } else {
            rope[knot] = Pair(
                rope[knot].first + rope[knot - 1].first.compareTo(rope[knot].first),
                rope[knot].second + rope[knot - 1].second.compareTo(rope[knot].second)
            )
            moveRope(rope, knot + 1)
        }
    }

    private fun areKnotsConnected(head: Knot, tail: Knot): Boolean =
        (abs(tail.first - head.first) < 2) && (abs(tail.second - head.second) < 2)
}

typealias Knot = Pair<Int, Int>
typealias Rope = LinkedList<Knot>