package days

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

/**
 * Created on 01.12.22
 *
 * @author vpiven
 */
internal class ADayTest {

    class TestDay(day: Number) : ADay(day) {
        override fun part1(): Number {
            return 0
        }

        override fun part2(): Number {
            return 0L
        }
    }

    @Test
    fun `Day number in the input name has always the size of 2`() {
        val day1 = TestDay(1)
        assertEquals("input_day_01.txt", day1.inputFile())

        val day15 = TestDay(15)
        assertEquals("input_day_15.txt", day15.inputFile())
    }
}