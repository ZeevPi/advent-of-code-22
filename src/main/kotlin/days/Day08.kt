package days

import utils.BoolUtils
import utils.FileReader

/**
 * Created on 08.12.22
 *
 * @author vpiven
 */
class Day08 : ADay(8) {

    private val matrix: Array<IntArray>

    init {
        matrix = FileReader.readLinesFromResource(inputFile())
            .map { lineToRow(it) }
            .toTypedArray()
    }

    private fun lineToRow(line: String): IntArray = line.toCharArray().map { it.digitToInt() }.toIntArray()

    override fun part1(): Number {
        val visibleTrees = (1 until matrix.size - 1).sumOf { row ->
            (1 until matrix.size - 1).map { column ->
                isTreeVisible(row, column)
            }.sumOf { BoolUtils.boolToInt(it) }
        }
        return visibleTrees + getEdgeTreeCount()
    }

    override fun part2(): Number {
        return (matrix.indices).maxOf { row ->
            (matrix.indices).maxOf { column ->
                computeScenicScore(row, column)
            }
        }
    }

    private fun getEdgeTreeCount(): Int = 4 * (matrix.size - 1)

    private fun isTreeVisible(row: Int, column: Int): Boolean {
        val isVisibleFromTop = lazy { (0 until row).maxOf { matrix[it][column] } < matrix[row][column] }
        val isVisibleFromLeft = lazy { (0 until column).maxOf { matrix[row][it] } < matrix[row][column] }
        val isVisibleFromBottom =
            lazy { (row + 1 until matrix.size).maxOf { matrix[it][column] } < matrix[row][column] }
        val isVisibleFromRight =
            lazy { (column + 1 until matrix.size).maxOf { matrix[row][it] } < matrix[row][column] }
        return isVisibleFromTop.value || isVisibleFromBottom.value || isVisibleFromLeft.value || isVisibleFromRight.value
    }

    private fun computeScenicScore(row: Int, column: Int): Int {
        val visibleTreesAbove = (row - 1 downTo 0)
            .find { (matrix[row][column] <= matrix[it][column]) }
            .let { row - (it ?: 0) }

        val visibleTreesLeft = (column - 1 downTo 0)
            .find { (matrix[row][column] <= matrix[row][it]) }
            .let { column - (it ?: 0) }

        val visibleTreesBelow = (row + 1 until matrix.size)
            .find { (matrix[row][column] <= matrix[it][column]) }
            .let { (it ?: (matrix.size - 1)) - row }

        val visibleTreesRight = (column + 1 until matrix.size)
            .find { (matrix[row][column] <= matrix[row][it]) }
            .let { (it ?: (matrix.size - 1)) - column }

        return visibleTreesAbove * visibleTreesBelow * visibleTreesLeft * visibleTreesRight
    }
}