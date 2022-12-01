import days.*

val DAYS: Map<Int, Day> = mapOf(Pair(1, Day01()))

fun main() {
    println("Hi there! Please enter the number of the day to get the appropriate solution: ")
    val day = readLine()!!.toInt()
    if (DAYS.containsKey(day)) {
        println("Solution of part one: ${DAYS[day]!!.part1()}")
        println("Solution of part two: ${DAYS[day]!!.part2()}")
    } else {
        println("Sorry, there is no solution for the day '$day'")
    }
}