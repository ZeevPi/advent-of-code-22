package utils

/**
 * Created on 08.12.22
 *
 * @author vpiven
 */
object BoolUtils {

    /**
     * Convert a [boolean][bool] to string.
     * @param bool boolean to convert.
     * @return Returns 1 if true, 0 otherwise.
     */
    fun boolToInt(bool: Boolean): Int = if (bool) 1 else 0
}