package com.emmav.adventofcode.`2015`

import java.io.File

fun main() {
    val liftInstructions = File("input/2015/day1-1.txt").readLines().first()

    println("part1: ${calculateFloor(liftInstructions)}")
}

internal fun calculateFloor(input: String): Int {
    return input.map { if (it == '(') 1 else -1 }.sum()
}