package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Created on 11.12.22
 *
 * @author vpiven
 */
internal class Day11Test {

    private val day = Day11()

    @Test
    fun part1() {
        assertEquals(10605, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(2713310158, day.part2())
    }
}