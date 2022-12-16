package days

import utils.FileReader
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.max

/**
 * Created on 16.12.22
 *
 * @author vpiven
 */
class Day16 : ADay(16) {

    private val tunnels: Map<String, Pair<Int, Map<String, Int>>>
    private val start = "AA"

    init {
        val lineRegex = Regex("Valve (\\w{2}) has flow rate=(\\d+); tunnels? leads? to valves? (.*)")

        // Read all tunnels
        val tunnels: MutableMap<String, Pair<Int, MutableMap<String, Int>>> =
            FileReader.readLinesFromResource(inputFile())
                .filter { lineRegex.matches(it) }
                .map { lineRegex.find(it)!!.groupValues }
                .associate { (_, name, rate, leadsTo) ->
                    Pair(
                        name,
                        Pair(rate.toInt(), leadsTo.split(", ").associateWith { _ -> 1 }.toMutableMap())
                    )
                }
                .toMutableMap()

        // Combine tunnels, so that each valve has a distance to all other valves
        for (v1 in tunnels.keys) {
            for (v2 in tunnels.keys) {
                for (v3 in tunnels.keys) {
                    if (v1 != v2 && v2 != v3 && v3 != v1
                        && v1 in tunnels[v2]!!.second // v1 can be accessed from v2
                        && v3 in tunnels[v1]!!.second // v3 can be accessed from v1
                    ) {
                        // Add or update connection between v2 and v3 in v2
                        val distance = tunnels[v2]!!.second[v1]!! + tunnels[v1]!!.second[v3]!!
                        if ((tunnels[v2]!!.second[v3] ?: Int.MAX_VALUE) > distance) {
                            tunnels[v2]!!.second[v3] = distance
                        }
                    }
                }
            }
        }

        // Remove 'useless' (flow=0) valves as we don't want to open them
        val valvesWithoutFlow = tunnels.filter { it.value.first == 0 }
            .map { it.key }
            .toSet()
        valvesWithoutFlow.forEach {
            if (it != start)
                tunnels.remove(it)
            tunnels.forEach { (_, u) -> u.second.remove(it) }
        }

        this.tunnels = tunnels
    }

    override fun part1(): Number {
        return dfs(30)
    }

    override fun part2(): Any {
        return dfs(26, true)
    }

    private fun dfs(maxTime: Int, withHelper: Boolean = false): Int {
        val maxPressure = AtomicInteger(0)
        dfsWithHelper(0, setOf(), 0, start, maxPressure, maxTime, !withHelper)
        return maxPressure.get()
    }

    private fun dfsWithHelper(
        pressure: Int,
        visited: Set<String>,
        time: Int,
        startValve: String,
        maxPressure: AtomicInteger,
        maxTime: Int,
        isHelper: Boolean = true
    ) {
        maxPressure.set(max(maxPressure.get(), pressure))
        for ((valve, distance) in tunnels[startValve]!!.second) {
            val newTime = time + distance + 1
            if (!visited.contains(valve) && (newTime < maxTime))
                dfsWithHelper(
                    pressure + (maxTime - newTime) * tunnels[valve]!!.first,
                    visited + setOf(valve),
                    newTime, valve, maxPressure, maxTime, isHelper
                )
        }
        if (!isHelper)
            dfsWithHelper(pressure, visited, 0, start, maxPressure, maxTime)
    }
}