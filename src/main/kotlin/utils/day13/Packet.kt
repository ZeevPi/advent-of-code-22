package utils.day13

/**
 * Created on 13.12.22
 *
 * @author vpiven
 */
sealed class Packet : Comparable<Packet> {
    override fun equals(other: Any?): Boolean = other is Packet && compareTo(other) == 0
    override fun hashCode(): Int = javaClass.hashCode()
}

data class IntContent(val value: Int) : Packet() {
    override fun compareTo(other: Packet): Int = when (other) {
        is IntContent -> this.value compareTo other.value
        is ListContent -> ListContent(listOf(this)) compareTo other
    }

    override fun hashCode(): Int = value
    override fun equals(other: Any?): Boolean = super.equals(other)

}

data class ListContent(val list: List<Packet>) : Packet() {
    override fun compareTo(other: Packet): Int {
        return when (other) {
            is IntContent -> this compareTo ListContent(listOf(other))
            is ListContent -> {
                val i = this.list.iterator()
                val j = other.list.iterator()
                while (i.hasNext() && j.hasNext()) {
                    val comparison = i.next() compareTo j.next()
                    if (comparison != 0) return comparison
                }
                i.hasNext() compareTo j.hasNext()
            }
        }
    }

    override fun hashCode(): Int = list.fold(0) { acc, value -> 31 * acc + value.hashCode() }
    override fun equals(other: Any?): Boolean = super.equals(other)
}
