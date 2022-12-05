package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Created on 05.12.22
 *
 * @author vpiven
 */
internal class Day05Test {

    private val day = Day05()

    @Test
    fun part1() {
        assertEquals("CMZ", day.part1())
    }

    @Test
    fun part2() {
        assertEquals("MCD", day.part2())
    }
}