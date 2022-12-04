package days

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

/**
 * Created on 04.12.22
 *
 * @author vpiven
 */
internal class Day04Test {

    private val day = Day04()

    @Test
    fun part1() {
        assertEquals(2, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(4, day.part2())
    }
}