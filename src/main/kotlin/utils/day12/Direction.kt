package utils.day12

/**
 * Created on 12.12.22
 *
 * @author vpiven
 */
enum class Direction(val x: Int, val y: Int) {
    UP(0, -1), DOWN(0, 1),
    LEFT(-1, 0), RIGHT(1, 0)
}