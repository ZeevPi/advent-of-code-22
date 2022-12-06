package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created on 06.12.22
 *
 * @author vpiven
 */
internal class Day06Test {

    private val day = Day06()

    @Test
    fun part1() {
        assertEquals(7, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(19, day.part2())
    }

    @ParameterizedTest(name = "Marker of length {1} is at position {2} in '{0}'")
    @MethodSource("getData")
    fun `Get the position of a marker with desired length`(input: String, length: Int, pos: Int) {
        val method = day.javaClass.getDeclaredMethod("findMarkerPos", String::class.java, Int::class.java)
        method.isAccessible = true
        assertEquals(pos, method.invoke(day, input, length))
    }

    companion object {
        @JvmStatic
        fun getData(): List<Arguments> {
            return listOf(
                Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz", 4, 5),
                Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz", 14, 23),
                Arguments.of("nppdvjthqldpwncqszvftbrmjlhg", 4, 6),
                Arguments.of("nppdvjthqldpwncqszvftbrmjlhg", 14, 23),
                Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4, 10),
                Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14, 29),
                Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4, 11),
                Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14, 26)
            )
        }
    }
}