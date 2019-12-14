import com.emmav.adventofcode.IntcodeComputer
import com.emmav.adventofcode.permutations
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

    part1(memory)
    part2(memory)
}

private fun part2(program: Array<Int>) {
    val max = listOf(5, 6, 7, 8, 9).permutations().map { calculateSignal(it, program.clone()) }.max()
    println("part2: $max")
}

private fun calculateSignal(
    phaseSequence: List<Int>,
    program: Array<Int>
): Int {
    val amplifiers = phaseSequence
        .map { IntcodeComputer(program.clone(), mutableListOf(it)) }

    var outputIndex = 0
    while (amplifiers.any { !it.isDone() }) {
        amplifiers.forEachIndexed { index, computer ->
            if (index == 0) {
                val output = amplifiers.last().output()
                if (output.isEmpty()) {
                    computer.addInput(0)
                } else {
                    computer.addInput(output[outputIndex - 1])
                }
            } else {
                computer.addInput(amplifiers[(index - 1) % 5].output()[outputIndex])
            }
        }
        outputIndex++
    }
    return amplifiers.last().output().last()
}

private fun part1(input: Array<Int>) {
    val max = listOf(0, 1, 2, 3, 4).permutations().map { calculateThrusterSignal(it, input) }.max()
    println("part1: $max")
}

private fun calculateThrusterSignal(phaseSequence: List<Int>, input: Array<Int>): Int {
    val amp1Result = amplifier(input1 = phaseSequence[0], input2 = 0, program = input)
    val amp2Result = amplifier(input1 = phaseSequence[1], input2 = amp1Result, program = input)
    val amp3Result = amplifier(input1 = phaseSequence[2], input2 = amp2Result, program = input)
    val amp4Result = amplifier(input1 = phaseSequence[3], input2 = amp3Result, program = input)
    return amplifier(input1 = phaseSequence[4], input2 = amp4Result, program = input)
}

fun amplifier(
    input1: Int,
    input2: Int,
    program: Array<Int>
): Int {
    val compute = IntcodeComputer(memory = program, inputList = mutableListOf(input1, input2)).apply { compute() }
    return compute.output().first()
}
