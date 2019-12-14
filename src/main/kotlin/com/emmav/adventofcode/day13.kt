package com.emmav.adventofcode

import java.io.File
import kotlin.math.absoluteValue

private val memory = File("input/day13.txt")
    .readLines()
    .first()
    .split(",")
    .map { it.toInt() }
    .toTypedArray()

fun main() {
    val computer = IntcodeComputer(memory = memory, inputList = mutableListOf()).apply { compute() }
    computer.output().forEach { println(it) }
}
