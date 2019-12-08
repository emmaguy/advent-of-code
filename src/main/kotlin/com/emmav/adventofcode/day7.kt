import com.emmav.adventofcode.IntcodeComputer
import java.io.File

val memory = File("input/day7.txt")
    .readLines()
    .first()
    .split(",")
    .map { it.toInt() }
    .toTypedArray()

fun main() {

    val test1 = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
        .split(",")
        .map { it.toInt() }
        .toTypedArray()

    part2(test1)
}

private fun part2(program: Array<Int>) {
    val phaseSequence = listOf(9, 8, 7, 6, 5)

    val ampAOutput = ampWithFeedback(phaseSequence[0], 0, program)
    val ampBOutput = ampWithFeedback(phaseSequence[1], ampAOutput, program)
    val ampCOutput = ampWithFeedback(phaseSequence[2], ampBOutput, program)
    val ampDOutput = ampWithFeedback(phaseSequence[3], ampCOutput, program)
    val ampEOutput = ampWithFeedback(phaseSequence[4], ampDOutput, program)

    print(ampEOutput)
}

private fun ampWithFeedback(phaseSetting: Int, input: Int, program: Array<Int>): Int {
    val programInput = mutableListOf(phaseSetting, input)
    val computer = IntcodeComputer()
    computer.newOutputCallback = {
        programInput.add(it)
    }
    val output = computer.compute(program, programInput)
    println("output from amp: $output")
    return output.last()
}

private fun part1(input: Array<Int>) {
    val inputSequences = mutableListOf<List<Int>>()
    // get lists of all combinations of 0-4
    for (i in 0..4) {
        for (j in 0..4) {
            for (k in 0..4) {
                for (l in 0..4) {
                    for (m in 0..4) {
                        if (i != j && i != k && i != l && i != m) {
                            if (j != k && j != l && j != m) {
                                if (k != l && k != m) {
                                    if (l != m) {
                                        inputSequences.add(listOf(i, j, k, l, m))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    val max = inputSequences.map { calculateThrusterSignal(it, input) }.max()
    print("max: $max")
}

private fun calculateThrusterSignal(phaseSequence: List<Int>, input: Array<Int>): Int {
    val computer = IntcodeComputer()
    val amp1Result = amplifier(input1 = phaseSequence[0], input2 = 0, computer = computer, program = input)
    val amp2Result = amplifier(input1 = phaseSequence[1], input2 = amp1Result, computer = computer, program = input)
    val amp3Result = amplifier(input1 = phaseSequence[2], input2 = amp2Result, computer = computer, program = input)
    val amp4Result = amplifier(input1 = phaseSequence[3], input2 = amp3Result, computer = computer, program = input)
    return amplifier(input1 = phaseSequence[4], input2 = amp4Result, computer = computer, program = input)
}

fun amplifier(
    input1: Int,
    input2: Int,
    computer: IntcodeComputer,
    program: Array<Int>
): Int {
    val compute = computer.compute(memory = program, input = listOf(input1, input2))
    return compute.first()
}
