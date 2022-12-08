package utils

/**
 * Created on 08.12.22
 *
 * @author vpiven
 */
object MatrixUtils {

    /**
     * Print a [matrix] to console
     * @param matrix the matrix to print.
     */
    fun displayMatrix(matrix: Array<IntArray>) {
        println("The matrix is: ")
        for (row in matrix) {
            for (column in row) {
                print("$column    ")
            }
            println()
        }
    }
}