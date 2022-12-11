package utils.day11

/**
 * Created on 11.12.22
 *
 * @author vpiven
 */
class Item(private val initValue: Int, private val operations: ArrayDeque<MonkeyOperation> = ArrayDeque()) {

    private val hash: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

    fun addOperation(operation: MonkeyOperation) = operations.addLast(operation)

    fun isDivisibleBy(divisor: Int, worryLevelReducer: ((Int) -> Int)?): Boolean {
        val hashedValue = hash[divisor]?.second ?: initValue
        val ops = operations.asSequence().drop(hash[divisor]?.first ?: 0)

        val res = ops.fold(hashedValue) { acc, op ->
            if (worryLevelReducer != null)
                worryLevelReducer(op.apply(acc))
            else
                op.applyWithModulo(acc, divisor)
        }
        hash[divisor] = Pair(operations.size, res)

        return res % divisor == 0
    }

    override fun toString(): String {
        return if (operations.size <= 20) {
            var acc: Int = initValue
            operations.forEach {
                acc = it.apply(acc)
            }
            acc.toString()
        } else {
            "$initValue [with ${operations.size} operations]"
        }
    }
}