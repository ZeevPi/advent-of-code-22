package utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Created on 08.12.22
 *
 * @author vpiven
 */
internal class BoolUtilsTest {

    @Test
    fun `true should always convert to 1 and false to 0`() {
        assertEquals(0, BoolUtils.boolToInt(false))
        assertEquals(1, BoolUtils.boolToInt(true))
    }
}