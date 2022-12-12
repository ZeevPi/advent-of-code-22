package days

import utils.FileReader
import utils.day12.ANode
import utils.day12.Grid

/**
 * Created on 12.12.22
 *
 * @author vpiven
 */
class Day12 : ADay(12) {

    override fun part1(): Number {
        return parseInput().shortestPath().size - 1
    }

    override fun part2(): Number {
        return parseInput().let { grid ->
            grid.nodes
                .flatMap { it.filter { node -> node.elev == 'a' } }
                .asSequence()
                .map { grid.copy(start = it) }
                .map { it.shortestPath() }
                .map { it.size - 1 }
                .filter { it != 0 }
                .minOf { it }
        }
    }

    private fun parseInput(): Grid = FileReader.readLinesFromResource(inputFile())
        .mapIndexed { y, str ->
            str.mapIndexed { x, char ->
                when (char) {
                    'S' -> ANode.Start(x, y)
                    'E' -> ANode.End(x, y)
                    else -> ANode.Node(x, y, char)
                }

            }
        }
        .fold(
            // Accumulator is an empty Grid
            Grid(ANode.Start(-1, -1), ANode.End(-1, -1), emptyList())
        ) { acc, nodes ->
            val start = nodes.firstOrNull { it is ANode.Start } ?: acc.start
            val end = nodes.firstOrNull { it is ANode.End } ?: acc.end
            acc.copy(start = start, end = end, nodes = acc.nodes + listOf(nodes))
        }
}