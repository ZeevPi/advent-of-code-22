package days

import utils.FileReader

/**
 * Created on 02.12.22
 *
 * @author vpiven
 */
class Day02 : ADay(2) {
    override fun part1(): Number {
        return FileReader.readLinesFromResource(inputFile())
            .asSequence()
            .map { s -> s.split(Regex("\\s+")).let { Pair(it[0], it[1]) } }
            .map { Pair(charToRPS(it.first), charToRPS(it.second)) }
            .map { Pair(computeOutcomeScore(it.second, it.first), it.second.score) }
            .map { it.first + it.second }
            .sum()
    }

    override fun part2(): Number {
        return FileReader.readLinesFromResource(inputFile())
            .asSequence()
            .map { s -> s.split(Regex("\\s+")).let { Pair(it[0], it[1]) } }
            .map { Pair(charToRPS(it.first), it.second) }
            .map { lineToScore(it) }
            .sum()
    }

    // Convert given character to a shape.
    private fun charToRPS(char: String): RPS =
        when (char) {
            "A", "X" -> RPS.ROCK
            "B", "Y" -> RPS.PAPER
            else -> RPS.SCISSORS
        }

    // Compute the score of a round of "Rock Paper Scissors" game.
    private fun computeOutcomeScore(me: RPS, elf: RPS): Int {
        return when (Math.floorMod(me.score - elf.score, 3)) {
            0 -> 3
            1 -> 6
            else -> 0
        }
    }

    // Convert an input line to a score.
    private fun lineToScore(line: Pair<RPS, String>): Int =
        when (line.second) {
            "X" -> 0 + line.first.prev().score
            "Y" -> 3 + line.first.score
            else -> 6 + line.first.next().score
        }

    /**
     * Enumeration to represent the shapes of "Rock Paper Scissors" game.
     */
    enum class RPS(val score: Int) {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        /**
         * Get a shape that beats the current one.
         */
        fun next(): RPS {
            val nextOrdinal = (ordinal + 1) % RPS.values().size
            return RPS.values()[nextOrdinal]
        }

        /**
         * Get a shape that loses to the current one.
         */
        fun prev(): RPS {
            val prevOrdinal = Math.floorMod(ordinal - 1, RPS.values().size)
            return RPS.values()[prevOrdinal]
        }
    }
}