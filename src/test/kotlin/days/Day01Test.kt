package days

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

/**
 * Created on 01.12.22
 *
 * @author vpiven
 */
internal class Day01Test {

    private val day = Day01()

    @Test
    fun part1() {
        assertEquals(24000L, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(45000L, day.part2())
    }
}