package utils.day11

/**
 * Created on 11.12.22
 *
 * @author vpiven
 */
class MonkeyOperation(private val a: String, private val b: String, private val sign: String) {

    fun apply(x: Int): Int {
        val a = parseSymbol(a, x)
        val b = parseSymbol(b, x)
        return createFunction(a, b)
    }

    fun applyWithModulo(x: Int, divisor: Int): Int {
        val a = parseSymbol(a, x) % divisor
        val b = parseSymbol(b, x) % divisor
        return createFunction(a, b) % divisor
    }

    private fun createFunction(a: Int, b: Int) = when (sign) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        else -> a / b
    }

    private fun parseSymbol(s: String, x: Int): Int = if ("old" == s) x else s.toInt()
}