package com.emmav.adventofcode

import java.io.File
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val inputAsteroidField = File("input/day10-1.txt").readLines()
        .mapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, character ->
                if (character == '#') {
                    Asteroid(columnIndex.toDouble(), rowIndex.toDouble())
                } else {
                    null
                }
            }.filterNotNull()
        }.flatten()
        .toSet()

    val asteroidForStation = part1(inputAsteroidField)
}

private fun part1(inputAsteroidField: Set<Asteroid>): Asteroid {
    val distances = inputAsteroidField
        .map { asteroid -> Pair(asteroid, inputAsteroidField.filter { it != asteroid }) }
        .map { (asteroid, asteroids) ->
            val map = mapOfAnglesAndDistances(asteroid, asteroids)
            Pair(asteroid, map.size)
        }
        .sortedBy { it.second } // Sort by the number of asteroids that can be seen

    val pair = distances.last()
    println(pair)
    return pair.first
}

private fun mapOfAnglesAndDistances(
    asteroid: Asteroid,
    asteroidField: List<Asteroid>
): Map<Double, List<Pair<Double, Double>>> {
    return asteroidField.map { b ->
        // Calculate the angle to each asteroid and the distance to it
        val angle = atan2(b.y - asteroid.y, b.x - asteroid.x)
        val distance = sqrt((b.x - asteroid.x).pow(2.0) + (b.y - asteroid.y).pow(2.0))
        Pair(angle, distance)
    }
        .groupBy { (slope, _) -> slope } // Group by the angle as only one can be visible on line of sight
        .toMap()
}

data class Asteroid(val x: Double, val y: Double)
