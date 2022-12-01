package days

/**
 * Created on 01.12.22
 *
 * @author vpiven
 */
abstract class ADay(private val dayNumber: Number) : Day {

    fun inputFile(): String = String.format("input_day_%02d.txt", dayNumber)
}