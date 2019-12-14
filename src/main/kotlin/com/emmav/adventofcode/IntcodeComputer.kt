package com.emmav.adventofcode

class IntcodeComputer(private val memory: Array<Int>, private val inputList: MutableList<Int>) {
    private var index = 0
    private var isFinished = false
    private var relativeBaseOffset = 0
    private val outputList = mutableListOf<Int>()

    fun addInput(input: Int) {
        inputList.add(input)
        compute()
    }

    fun isDone() = isFinished

    fun output() = outputList.toList()

    fun compute() {
        while (true) {
            val (instruction, params) = instructionAndParams(memory[index])
            println("instruction: ${instruction.type}")
            when (instruction.type) {
                InstructionType.Add -> {
                    memory[params[2]] = memory[params[0]] + memory[params[1]]
                }
                InstructionType.Multiply -> {
                    memory[params[2]] = memory[params[0]] * memory[params[1]]
                }
                InstructionType.Input -> {
                    if (inputList.isEmpty()) {
                        println("no input, halting")
                        return
                    } else {
                        memory[params[0]] = inputList.removeAt(0)
                        println("input: ${memory[params[0]]}")
                    }
                }
                InstructionType.Output -> {
                    outputList.add(memory[params[0]])
                    println("output: ${memory[params[0]]}")
                }
                InstructionType.JumpIfTrue -> {
                    if (memory[params[0]] != 0) {
                        index = memory[params[1]] - instruction.type.length()
                    }
                }
                InstructionType.JumpIfFalse -> {
                    if (memory[params[0]] == 0) {
                        index = memory[params[1]] - instruction.type.length()
                    }
                }
                InstructionType.LessThan -> {
                    memory[params[2]] = if (memory[params[0]] < memory[params[1]]) 1 else 0
                }
                InstructionType.Equals -> {
                    memory[params[2]] = if (memory[params[0]] == memory[params[1]]) 1 else 0
                }
                InstructionType.Halt -> {
                    isFinished = true
                    return
                }
                InstructionType.RelativeOffset -> {
                    relativeBaseOffset = memory[params[0]]
                    println("relativeBaseOffset: $relativeBaseOffset")
                }
            }
            index += instruction.type.length()
        }
    }

    // Do operator overloading to get and set data in the intcode computer's memory
    private operator fun Array<Int>.get(parameter: Parameter): Int {
        return when (parameter.mode) {
            ParameterMode.Position -> this[parameter.value]
            ParameterMode.Immediate -> parameter.value
            ParameterMode.Relative -> this[parameter.value + relativeBaseOffset]
        }
    }

    private operator fun Array<Int>.set(parameter: Parameter, value: Int) {
        when (parameter.mode) {
            ParameterMode.Position -> this[parameter.value] = value
            ParameterMode.Immediate -> throw RuntimeException("Immediate mode not supported for assignment")
            ParameterMode.Relative -> this[parameter.value + relativeBaseOffset] = value
        }
    }

    private fun instructionAndParams(opcode: Int): Pair<Instruction, List<Parameter>> {
        val instruction = opcode.toInstruction()
        val modes = opcode.toString().dropLast(2)
            .padStart(3, '0')
            .reversed()
            .map { it.toString().toInt() }
        val params = (0 until instruction.type.numberOfParams)
            .map { Parameter(memory[index + 1 + it], modes[it].toParameterType()) }

        return Pair(instruction, params)
    }
}

private fun Int.toParameterType(): ParameterMode {
    return when (this) {
        0 -> ParameterMode.Position
        1 -> ParameterMode.Immediate
        2 -> ParameterMode.Relative
        else -> throw IllegalArgumentException("Unexpected parameter type: $this")
    }
}

data class Instruction(val type: InstructionType)

private fun Int.toInstruction(): Instruction {
    return Instruction(toString().takeLast(2).toInt().toType())
}

private fun Int.toType(): InstructionType {
    return when (this) {
        1 -> InstructionType.Add
        2 -> InstructionType.Multiply
        3 -> InstructionType.Input
        4 -> InstructionType.Output
        5 -> InstructionType.JumpIfTrue
        6 -> InstructionType.JumpIfFalse
        7 -> InstructionType.LessThan
        8 -> InstructionType.Equals
        9 -> InstructionType.RelativeOffset
        99 -> InstructionType.Halt
        else -> throw IllegalArgumentException("Unexpected instruction: $this")
    }
}

data class Parameter(val value: Int, val mode: ParameterMode)

enum class ParameterMode {
    Relative,
    Position,
    Immediate
}

enum class InstructionType(val numberOfParams: Int) {
    Add(numberOfParams = 3),
    Multiply(numberOfParams = 3),
    Input(numberOfParams = 1),
    Output(numberOfParams = 1),
    JumpIfTrue(numberOfParams = 2),
    JumpIfFalse(numberOfParams = 2),
    LessThan(numberOfParams = 3),
    Equals(numberOfParams = 3),
    RelativeOffset(numberOfParams = 1),
    Halt(numberOfParams = 0);

    fun length() = numberOfParams + 1
}