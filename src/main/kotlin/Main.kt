import days.Day
import kotlin.reflect.full.primaryConstructor

fun main() {
    println("Hi there! Please enter the number of the day to get the appropriate solution: ")
    val input = readLine()!!
    try {
        val dayNum = input.toInt()
        val day = Class.forName(String.format("days.Day%02d", dayNum)).kotlin.primaryConstructor!!.call() as Day
        println("Solution of part one: ${day.part1()}")
        println("Solution of part two: ${day.part2()}")
    } catch (e: Exception) {
        when (e) {
            is ClassNotFoundException -> println("Sorry, there is no solution for the day '$input'")
            is NumberFormatException -> println("Sorry, but you have to enter a valid day. '$input' is invalid!")
            else -> throw e
        }
    }
}