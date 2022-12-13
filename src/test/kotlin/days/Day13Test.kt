package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Created on 13.12.22
 *
 * @author vpiven
 */
internal class Day13Test {

    private val day = Day13()

    @Test
    fun part1() {
        assertEquals(13, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(140, day.part2())
    }
}