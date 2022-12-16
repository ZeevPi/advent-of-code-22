package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Created on 16.12.22
 *
 * @author vpiven
 */
class Day16Test {

    private val day = Day16()

    @Test
    fun part1() {
        assertEquals(1651, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(1707, day.part2())
    }
}