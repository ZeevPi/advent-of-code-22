package utils.day11

/**
 * Created on 11.12.22
 *
 * @author vpiven
 */
class Monkey private constructor(
    val name: Int,
    private val items: ArrayDeque<Int>,
    private val operation: (Int) -> Int,
    val test: (Int) -> Int
) {

    companion object {
        fun parseMonkey(defs: List<String>): Monkey {
            if (defs.size != 6) {
                throw Exception("Cannot create monkey from input $defs")
            }

            val name = parseGroupedIntFromString("Monkey (\\d+):", defs[0])
            val items = if (Regex("\\s+Starting items:.*").matches(defs[1]))
                Regex("\\d+").findAll(defs[1])
                    .map { it.groupValues[0] }
                    .map { it.toInt() }
                    .toList()
            else listOf()

            val operation: (Int) -> Int = Regex("\\s+Operation: new = (old|\\d+) ([+*-/]) (old|\\d+)").find(defs[2])
                ?.groupValues
                ?.drop(1)
                ?.let { createOperation(it) }
                ?: { -1 }

            val testCond: Int = parseGroupedIntFromString("\\s+Test: divisible by (\\d+)", defs[3])
            val testIfTrue: Int = parseGroupedIntFromString("\\s+If true: throw to monkey (\\d+)", defs[4])
            val testIfFalse: Int = parseGroupedIntFromString("\\s+If false: throw to monkey (\\d+)", defs[5])

            val test: (Int) -> Int = { x -> if (x % testCond == 0) testIfTrue else testIfFalse }

            return Monkey(name, ArrayDeque(items), operation, test)
        }

        private fun parseGroupedIntFromString(regex: String, s: String, orElseDefault: Int = -1): Int =
            Regex(regex).find(s)
                ?.groupValues?.get(1)?.toInt()
                ?: orElseDefault

        private fun createOperation(description: List<String>): (Int) -> Int {
            val (f1, sign, f2) = description
            return { x ->
                val a = parseOpSymbol(f1, x)
                val b = parseOpSymbol(f2, x)
                when (sign) {
                    "+" -> a + b
                    "-" -> a - b
                    "*" -> a * b
                    else -> a / b
                }
            }
        }

        private fun parseOpSymbol(s: String, x: Int): Int = if ("old" == s) x else s.toInt()
    }

    var inspectedItems = 0
        private set

    fun inspectItem(worryLevelManager: (Int) -> Int): Int {
        inspectedItems++
        return worryLevelManager(operation(items.removeFirst()))
    }

    fun hasItems() = items.isNotEmpty()

    fun receiveItem(item: Int) = items.addLast(item)

    override fun toString(): String {
        return "Monkey $name (#inspections=$inspectedItems): $items"
    }
}