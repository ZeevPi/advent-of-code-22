package utils.day12

/**
 * Created on 12.12.22
 *
 * @author vpiven
 */
data class Grid(val start: ANode, val end: ANode, val nodes: List<List<ANode>>) {

    fun shortestPath(): List<ANode> {
        val path = mutableMapOf<ANode, ANode>()
        val queue = ArrayDeque<ANode>()
        val visited = mutableMapOf<ANode, Boolean>()

        queue.addFirst(start)
        visited[start] = true

        while (queue.size > 0) {
            val current = queue.removeLast()
            if (current == end) break

            Direction.values()
                .mapNotNull { adjacentNode(current, it) }
                .filter { !visited.getOrDefault(it, false) }
                .filter { current.isValid(it) }
                .forEach {
                    queue.addFirst(it)
                    path[it] = current
                    visited[it] = true
                }
        }

        return path.regeneratePath(end)
    }

    private fun adjacentNode(current: ANode, direction: Direction): ANode? {
        val newX = current.x + direction.x
        val newY = current.y + direction.y
        return when {
            newX !in nodes.first().indices -> null
            newY !in nodes.indices -> null
            else -> nodes[newY][newX]
        }
    }

    private fun Map<ANode, ANode>.regeneratePath(tail: ANode): List<ANode> {
        return when (val next = this[tail]) {
            null -> return listOf<ANode>() + tail
            else -> regeneratePath(next) + tail
        }
    }
}

sealed class ANode(open val x: Int, open val y: Int, open val elev: Char) {

    fun isValid(other: ANode) = this.elev + 1 >= other.elev

    data class Start(override val x: Int, override val y: Int) : ANode(x, y, 'a')

    data class End(override val x: Int, override val y: Int) : ANode(x, y, 'z')

    data class Node(override val x: Int, override val y: Int, override val elev: Char) : ANode(x, y, elev)
}