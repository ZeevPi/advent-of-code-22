package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Created on 04.12.22
 *
 * @author vpiven
 */
internal class Day03Test {

    private val day = Day03()

    @Test
    fun part1() {
        assertEquals(157, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(70, day.part2())
    }
}