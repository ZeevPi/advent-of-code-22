package utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

/**
 * Created on 01.12.22
 *
 * @author vpiven
 */
class FileReaderTest {

    @Test
    fun `Read 5 lines from existing file`() {
        val lines = FileReader.readLinesFromResource("test.txt")
        assertEquals(5, lines.size)
    }

    @Test
    fun `Read lines from not existing file returns an empty list`() {
        val lines = FileReader.readLinesFromResource("notExistingFile.txt")
        assertEquals(0, lines.size)
    }
}