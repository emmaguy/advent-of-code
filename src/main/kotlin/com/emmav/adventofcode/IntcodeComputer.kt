package com.emmav.adventofcode

class IntcodeComputer {

    var newOutputCallback: ((Int) -> Unit)? = null

    fun compute(memory: Array<Int>, input: List<Int>): List<Int> {
        val output = mutableListOf<Int>()
        var index = 0
        var inputIndex = 0
        while (true) {
            val instruction = memory[index]
            when (instruction % 100) {
                1 -> {
                    // Add
                    memory[memory[index + 3]] = param1(instruction, memory, index) + param2(instruction, memory, index)
                    println("add, result: ${memory[memory[index + 3]]}")
                    index += 4
                }
                2 -> {
                    // Multiply
                    memory[memory[index + 3]] = param1(instruction, memory, index) * param2(instruction, memory, index)
                    println("multiply, result: ${memory[memory[index + 3]]}")
                    index += 4
                }
                3 -> {
                    // Read from input
                    val address = memory[index + 1]
                    val inp = input[inputIndex++]
                    println("input: $inp")
                    memory[address] = inp
                    index += 2
                }
                4 -> {
                    // Write to output
                    val out = param1(instruction, memory, index)
                    output.add(out)
                    println("output: $out")
                    newOutputCallback?.invoke(out)
                    index += 2
                }
                5 -> {
                    // Jump if true
                    if (param1(instruction, memory, index) != 0) {
                        index = param2(instruction, memory, index)
                    } else {
                        index += 3
                    }
                }
                6 -> {
                    // Jump if false
                    if (param1(instruction, memory, index) == 0) {
                        index = param2(instruction, memory, index)
                    } else {
                        index += 3
                    }
                }
                7 -> {
                    // Less than
                    val value = if (param1(instruction, memory, index) < param2(instruction, memory, index)) {
                        1
                    } else {
                        0
                    }
                    memory[memory[index + 3]] = value
                    index += 4
                }
                8 -> {
                    // Equals
                    val value = if (param1(instruction, memory, index) == param2(instruction, memory, index)) {
                        1
                    } else {
                        0
                    }
                    memory[memory[index + 3]] = value
                    index += 4
                }
                99 -> return output // Halt
                else -> throw IllegalArgumentException("Unexpected instruction: $instruction")
            }
        }
    }

    private fun param1(instruction: Int, memory: Array<Int>, index: Int): Int {
        return if ((instruction / 100) % 10 == 1) memory[index + 1] else memory[memory[index + 1]]
    }

    private fun param2(instruction: Int, memory: Array<Int>, index: Int): Int {
        return if ((instruction / 1000) % 10 == 1) memory[index + 2] else memory[memory[index + 2]]
    }
}