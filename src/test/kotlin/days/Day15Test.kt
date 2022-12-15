package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

/**
 * Created on 15.12.22
 *
 * @author vpiven
 */
class Day15Test {

    companion object {
        private val day = Day15()

        @BeforeAll
        @JvmStatic
        fun init() {
            val yField = day.javaClass.getDeclaredField("y")
            yField.isAccessible = true
            yField.set(day, 10)

            val maxRangeField = day.javaClass.getDeclaredField("maxRange")
            maxRangeField.isAccessible = true
            maxRangeField.set(day, 20)
        }
    }

    @Test
    fun part1() {
        assertEquals(26, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(56_000_011L, day.part2())
    }
}