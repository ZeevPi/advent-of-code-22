package utils

/**
 * Created on 01.12.22
 *
 * @author vpiven
 */
object FileReader {

    fun readLinesFromResource(resourceName: String): List<String> =
        javaClass.classLoader.getResourceAsStream(resourceName)?.bufferedReader()?.readLines()
            ?: emptyList()

}