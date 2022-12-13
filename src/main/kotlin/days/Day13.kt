package days

import utils.FileReader
import utils.day13.IntContent
import utils.day13.ListContent
import utils.day13.Packet

/**
 * Created on 13.12.22
 *
 * @author vpiven
 */
class Day13 : ADay(13) {

    private val packets = FileReader.readLinesFromResource(inputFile())
        .asSequence()
        .filter { it.isNotBlank() }
        .map { parseLine(it) }

    override fun part1(): Number {
        return packets
            .chunked(2) { (a, b) -> a <= b }
            .withIndex()
            .filter { it.value }
            .sumOf { it.index + 1 }
    }

    override fun part2(): Any {
        val dp2: Packet = ListContent(listOf(ListContent(listOf(IntContent(2)))))
        val dp6: Packet = ListContent(listOf(ListContent(listOf(IntContent(6)))))

        var psBeforeDp2 = 1
        var psBeforeDp6 = 2 // as dp2 < dp6

        for (packet in packets) {
            if (packet < dp2)
                psBeforeDp2++
            if (packet < dp6)
                psBeforeDp6++
        }
        return psBeforeDp2 * psBeforeDp6
    }

    private fun parseLine(line: String): Packet {
        val (index, packet) = parser.invoke(IndexedValue(0, line))
        require(index == line.length)
        return packet
    }

    private val parser = DeepRecursiveFunction<IndexedValue<String>, IndexedValue<Packet>> { (startIndex, string) ->
        var index = startIndex + 1
        if (string[startIndex] == '[') {
            // Parse list content
            val list = buildList {
                while (string[index] != ']') {
                    val (endIndex, value) = callRecursive(IndexedValue(index, string))
                    add(value)
                    index = if (string[endIndex] == ',') endIndex + 1 else endIndex
                }
            }
            IndexedValue(index + 1, ListContent(list))
        } else {
            // Parse integer content
            while (index < string.length && string[index] in '0'..'9') index++
            IndexedValue(index, IntContent(string.substring(startIndex, index).toInt()))
        }
    }
}