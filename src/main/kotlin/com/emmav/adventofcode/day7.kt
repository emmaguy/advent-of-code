import com.emmav.adventofcode.IntcodeComputer
import java.io.File

fun main() {
    val memory = File("input/day7.txt")
        .readLines()
        .first()
        .split(",")
        .map { it.toInt() }
        .toTypedArray()

    val test1 = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"
        .split(",")
        .map { it.toInt() }
        .toTypedArray()

    part1(memory)
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
