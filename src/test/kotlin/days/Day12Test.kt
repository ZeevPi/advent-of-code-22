package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Created on 12.12.22
 *
 * @author vpiven
 */
internal class Day12Test {

    private val day = Day12()

    @Test
    fun part1() {
        assertEquals(31, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(29, day.part2())
    }
}