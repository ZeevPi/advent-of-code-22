package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Created on 08.12.22
 *
 * @author vpiven
 */
internal class Day08Test {

    private val day = Day08()

    @Test
    fun part1() {
        assertEquals(21, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(8, day.part2())
    }
}