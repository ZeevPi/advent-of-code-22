package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Created on 09.12.22
 *
 * @author vpiven
 */
internal class Day09Test {

    private val day = Day09()

    @Test
    fun part1() {
        assertEquals(13, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(1, day.part2())
    }
}