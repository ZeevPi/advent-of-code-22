package utils.day11

/**
 * Created on 11.12.22
 *
 * @author vpiven
 */
class Monkey private constructor(
    val name: Int,
    private val items: ArrayDeque<Item>,
    private val operation: MonkeyOperation,
    val test: (Item, ((Int) -> Int)?) -> Int
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
                    .map { Item(it.toInt()) }
                    .toList()
            else listOf()

            val operation: MonkeyOperation =
                Regex("\\s+Operation: new = (old|\\d+) ([+*-/]) (old|\\d+)").find(defs[2])
                    ?.groupValues
                    ?.drop(1)
                    ?.let { MonkeyOperation(it[0], it[2], it[1]) }
                    ?: throw Exception("Can not parse monkey operation")

            val testCond: Int = parseGroupedIntFromString("\\s+Test: divisible by (\\d+)", defs[3])
            val testIfTrue: Int = parseGroupedIntFromString("\\s+If true: throw to monkey (\\d+)", defs[4])
            val testIfFalse: Int = parseGroupedIntFromString("\\s+If false: throw to monkey (\\d+)", defs[5])

            val test: (Item, ((Int) -> Int)?) -> Int =
                { item, reducer -> if (item.isDivisibleBy(testCond, reducer)) testIfTrue else testIfFalse }

            return Monkey(name, ArrayDeque(items), operation, test)
        }

        private fun parseGroupedIntFromString(regex: String, s: String, orElseDefault: Int = -1): Int =
            Regex(regex).find(s)
                ?.groupValues?.get(1)?.toInt()
                ?: orElseDefault
    }

    var inspectedItems = 0
        private set

    fun inspectItem(): Item {
        inspectedItems++
        val item = items.removeFirst()
        item.addOperation(operation)
        return item
    }

    fun hasItems() = items.isNotEmpty()

    fun receiveItem(item: Item) = items.addLast(item)

    override fun toString(): String {
        return "Monkey $name (#inspections=$inspectedItems): $items"
    }
}