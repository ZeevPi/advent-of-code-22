package days

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import utils.FileReader

/**
 * Created on 02.12.22
 *
 * @author vpiven
 */
internal class Day02Test {

    private val day = Day02()

    @Test
    fun part1() {
        assertEquals(15, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(12, day.part2())
    }

    @Test
    fun `Compute a score of a game mathematically`() {
        val method = day.javaClass.getDeclaredMethod("computeOutcomeScore", Day02.RPS::class.java, Day02.RPS::class.java)
        method.isAccessible = true
        assertEquals(6, method.invoke(day, Day02.RPS.PAPER, Day02.RPS.ROCK))
        assertEquals(0, method.invoke(day, Day02.RPS.ROCK, Day02.RPS.PAPER))
        assertEquals(0, method.invoke(day, Day02.RPS.SCISSORS, Day02.RPS.ROCK))
        assertEquals(3, method.invoke(day, Day02.RPS.PAPER, Day02.RPS.PAPER))
    }

    @Test
    fun `Next value in RPS should beat the current one`() {
        assertEquals(Day02.RPS.PAPER, Day02.RPS.ROCK.next())
        assertEquals(Day02.RPS.SCISSORS, Day02.RPS.PAPER.next())
        assertEquals(Day02.RPS.ROCK, Day02.RPS.SCISSORS.next())
    }

    @Test
    fun `Previous value in RPS should loose to the current one`() {
        assertEquals(Day02.RPS.SCISSORS, Day02.RPS.ROCK.prev())
        assertEquals(Day02.RPS.ROCK, Day02.RPS.PAPER.prev())
        assertEquals(Day02.RPS.PAPER, Day02.RPS.SCISSORS.prev())
    }
}