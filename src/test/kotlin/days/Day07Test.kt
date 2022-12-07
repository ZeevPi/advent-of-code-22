package days

import days.Day07.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

/**
 * Created on 07.12.22
 *
 * @author vpiven
 */
internal class Day07Test {

    private val day = Day07()

    @Test
    fun part1() {
        assertEquals(95437, day.part1())
    }

    @Test
    fun part2() {
        assertEquals(24933642, day.part2())
    }

    @Test
    fun `Parsing of a valid line works as expected`() {
        val testDay = Day07()
        val rootField = testDay.javaClass.getDeclaredField("root")
        rootField.isAccessible = true
        val root = rootField.get(testDay) as Dir
        root.children.clear()

        val method = testDay.javaClass.getDeclaredMethod("parseLine", String::class.java)
        method.isAccessible = true
        method.invoke(testDay, "$ cd /")
        method.invoke(testDay, "$ ls")
        method.invoke(testDay, "42 42")
        method.invoke(testDay, "dir abc")
        method.invoke(testDay, "$ cd abc")
        method.invoke(testDay, "1234 test.txt")

        val prettyRoot =
            """
                - / (dir, size=1276)
                  - 42 (file, size=42)
                  - abc (dir, size=1234)
                    - test.txt (file, size=1234)
                
            """.trimIndent()

        assertEquals(prettyRoot, root.toPrettyString(""))
    }

    @Test
    fun `Parsing of a invalid lines should throw an Exception`() {
        val testDay = Day07()
        val rootField = testDay.javaClass.getDeclaredField("root")
        rootField.isAccessible = true
        val root = rootField.get(testDay) as Dir
        root.children.clear()

        val method = testDay.javaClass.getDeclaredMethod("parseLine", String::class.java)
        method.isAccessible = true
        method.invoke(testDay, "$ cd /")

        assertFailsWith(
            exceptionClass = Exception::class,
            message = "You are going out of root!",
            block = {
                method.invoke(testDay, "$ cd ..")
            }
        )

        val dirName = "aaccasd"
        assertFailsWith(
            exceptionClass = Exception::class,
            message = "Desired dir '$dirName' is not a child of '/'",
            block = {
                method.invoke(testDay, "$ cd $dirName")
            }
        )
    }

    @Test
    fun `The size of a file is always the same as by creation`() {
        val (file1, file2, _, _) = prepareTestData()

        assertEquals(1234, file1.size())
        assertEquals(42, file2.size())
    }

    @Test
    fun `The size of a directory is a sum of all files in it and its subdirectories`() {
        val (_, _, abc, xyz) = prepareTestData()

        assertEquals(1276, xyz.size())
        assertEquals(1234, abc.size())
    }

    @Test
    fun `To string of a file system element returns its name, type and size`() {
        val (file1, file2, abc, xyz) = prepareTestData()

        assertEquals("test.txt (file, size=1234)", file1.toString())
        assertEquals("42 (file, size=42)", file2.toString())
        assertEquals("abc (dir, size=1234)", abc.toString())
        assertEquals("xyz (dir, size=1276)", xyz.toString())
    }

    @Test
    fun `If a desired directory is within the selected one than it should be returned`() {
        val (_, _, abc, xyz) = prepareTestData()

        assertEquals(abc, (xyz as Dir).getDirByName("abc"))
        assertNull(xyz.getDirByName("blub"))
    }

    @Test
    fun `A file system elements can be printed in a pretty way`() {
        val (_, _, _, xyz) = prepareTestData()
        val prettyXyz =
            """
                - xyz (dir, size=1276)
                  - 42 (file, size=42)
                  - abc (dir, size=1234)
                    - test.txt (file, size=1234)
                
            """.trimIndent()

        assertEquals(prettyXyz, xyz.toPrettyString(""))
    }

    private fun prepareTestData(): List<FileSystemElem> {
        val file1 = File("test.txt", 1234)
        val file2 = File("42", 42)
        val xyz = Dir("xyz", null)
        xyz.add(file2)
        val abc = Dir("abc", xyz)
        abc.add(file1)

        return listOf(file1, file2, abc, xyz)
    }
}