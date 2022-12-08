package utils

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Created on 08.12.22
 *
 * @author vpiven
 */
internal class MatrixUtilsTest {

    companion object {
        private val buffer = ByteArrayOutputStream()
        private val newStdOut = PrintStream(buffer)
        private val originalStdOut = System.out

        @BeforeAll
        @JvmStatic
        fun init() = System.setOut(newStdOut)

        @BeforeEach
        fun cleanBuffer() = buffer.reset()

        @AfterAll
        @JvmStatic
        fun cleanUp() = System.setOut(originalStdOut)
    }

    @Test
    fun `matrix should be properly printed`() {
        val matrix = arrayOf(arrayOf(1, 2, 3).toIntArray(), arrayOf(4, 5, 6).toIntArray())
        val res = "The matrix is: \n1    2    3    \n4    5    6    \n"
        MatrixUtils.displayMatrix(matrix)
        assertEquals(res, buffer.toString())
    }
}