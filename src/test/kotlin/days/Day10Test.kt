package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Created on 10.12.22
 *
 * @author vpiven
 */
internal class Day10Test {

    private val day = Day10()

    @Test
    fun part1() {
        assertEquals(13140, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(
            """
                
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....
                
            """.trimIndent(),
            day.part2())
    }
}