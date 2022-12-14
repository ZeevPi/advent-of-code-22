package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Created on 14.12.22
 *
 * @author vpiven
 */
class Day14Test {

    private val day = Day14()

    @Test
    fun part1() {
        assertEquals(24, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(93, day.part2())
    }
}