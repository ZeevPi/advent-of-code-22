package utils

import kotlin.math.abs

/**
 * Created on 15.12.22
 *
 * @author vpiven
 */
data class Point2D(val x: Int, val y: Int) {
    infix fun manhattanDistanceTo(other: Point2D) = abs(x - other.x) + abs(y - other.y)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point2D

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}
