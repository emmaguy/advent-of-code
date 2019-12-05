import java.lang.IllegalArgumentException

fun main() {
    intcodeComputer(memory = arrayOf(3, 0, 4, 0, 99))
}

fun intcodeComputer(memory: Array<Int>): Int {
    var index = 0
    while (true) {
        val instruction = memory[index]
        when (instruction) {
            1 -> {
                memory[memory[index + 3]] = memory[memory[index + 1]] + memory[memory[index + 2]]
                index += 4
            }
            2 -> {
                memory[memory[index + 3]] = memory[memory[index + 1]] * memory[memory[index + 2]]
                index += 4
            }
            3 -> {
                input(memory, memory[index + 1])
                index += 2
            }
            4 -> {
                output(memory, memory[index + 1])
                index += 2
            }
            99 -> return memory[0]
            else -> throw IllegalArgumentException("Unexpected instruction: $instruction")
        }
    }
}

private fun input(memory: Array<Int>, index: Int) {
    val input = readLine()!!
    memory[index] = input.toInt()
    print("input: $input, stored in index $index\n")
}

private fun output(memory: Array<Int>, index: Int) {
    print("output: ${memory[index]}\n")
}