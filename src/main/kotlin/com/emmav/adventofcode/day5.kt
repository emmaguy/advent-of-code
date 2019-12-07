import java.lang.IllegalArgumentException

fun main() {
    intcodeComputer(memory = arrayOf(3, 0, 4, 0, 99))
}

fun intcodeComputer(memory: Array<Int>) {
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
                print("Awaiting input: ")
                val input = readLine()!!
                memory[index + 1] = input.toInt()
                print("input: $input, stored in index $index\n")
                index += 2
            }
            4 -> {
                print("output: ${memory[index + 1]}\n")
                index += 2
            }
            99 -> return
            else -> throw IllegalArgumentException("Unexpected instruction: $instruction")
        }
    }
}
