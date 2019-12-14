package com.emmav.adventofcode

import java.io.File
import kotlin.math.absoluteValue

private val reactions = File("input/day14-1.txt").readLines()
    .map {
        val inputsAndOutputs = it.split("=>")
        val inputs = inputsAndOutputs[0].split(",")
        val chemicals = inputs.map { input -> input.toChemical() }
        Reaction(chemicals, output = inputsAndOutputs[1].toChemical())
    }

private fun String.toChemical(): Chemical {
    val amountAndChemical = trim().split(" ")
    return Chemical(amountAndChemical[1].trim(), amountAndChemical[0].trim().toInt())
}

fun main() {

    reactions.forEach { println(it) }
}

data class Reaction(val chemicals: List<Chemical>, val output: Chemical)
data class Chemical(val name: String, val amount: Int)