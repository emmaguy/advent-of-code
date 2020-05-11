package com.emmav.adventofcode.`2015`

import java.io.File

fun main() {
    val directions = File("input/2015/day3.txt").readLines().first()

    println("part1: ${housesThatReceiveAtLeastOnePresent(directions)}")
//    println("part2: ${dimensions.map { ribbonRequired(it) }.sum()}")
}

internal fun housesThatReceiveAtLeastOnePresent(directionsString: String): Int {
    val directions = directionsString.toDirections()

    val coordinatesVisited = mutableMapOf<String, Int>()
    coordinatesVisited.append("(0,0)") // Starting point

    var xPos = 0
    var yPos = 0

    directions.forEach {
        when (it) {
            Direction.UP -> ++yPos
            Direction.DOWN -> --yPos
            Direction.LEFT -> ++xPos
            Direction.RIGHT -> --xPos
        }
        println("visiting ($xPos,$yPos)")
        coordinatesVisited.append("($xPos,$yPos)")
    }

    return coordinatesVisited.size
}

private fun MutableMap<String, Int>.append(key: String) {
    val existingValue = getOrDefault(key, defaultValue = 0)
    this[key] = existingValue + 1
}

private fun String.toDirections(): List<Direction> {
    return map {
        when (it) {
            '^' -> Direction.UP
            '>' -> Direction.RIGHT
            '<' -> Direction.LEFT
            'v' -> Direction.DOWN
            else -> throw IllegalArgumentException("Unexpected direction: $it")
        }
    }.toList()
}

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}



