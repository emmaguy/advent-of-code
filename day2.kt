val input = arrayOf(1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 10, 1, 19, 2, 19, 6, 23, 2, 13, 23, 27, 1, 9, 27, 31, 2, 31, 9, 35, 1, 6, 35, 39, 2, 10, 39, 43, 1, 5, 43, 47, 1, 5, 47, 51, 2, 51, 6, 55, 2, 10, 55, 59, 1, 59, 9, 63, 2, 13, 63, 67, 1, 10, 67, 71, 1, 71, 5, 75, 1, 75, 6, 79, 1, 10, 79, 83, 1, 5, 83, 87, 1, 5, 87, 91, 2, 91, 6, 95, 2, 6, 95, 99, 2, 10, 99, 103, 1, 103, 5, 107, 1, 2, 107, 111, 1, 6, 111, 0, 99, 2, 14, 0, 0)

fun main() {
    print("size: ${input.size}\n")
    input[1] = 12
    input[2] = 2
    processOpcode()

    val output = input.joinToString(",")
    print("\n$output")
}

fun processOpcode() {
    var index = 0

    while (true) {
        val opcode = input[index]
        when (opcode) {
            1 -> opcode1(index + 1, index + 2, index + 3)
            2 -> opcode2(index + 1, index + 2, index + 3)
            99 -> return
        }
        index += 4
    }
}

fun opcode1(index1: Int, index2: Int, index3: Int) {
    print("firstIndex: ${input[index1]}, secondIndex: ${input[index2]}, indexToStoreAt: ${input[index3]}\n")
    print("first: ${input[input[index1]]}, second: ${input[input[index2]]}\n")
    input[input[index3]] = input[input[index1]] + input[input[index2]]
}

fun opcode2(index1: Int, index2: Int, index3: Int) {
    print("firstIndex: ${input[index1]}, secondIndex: ${input[index2]}, indexToStoreAt: ${input[index3]}\n")
    print("first: ${input[input[index1]]}, second: ${input[input[index2]]}\n")
    input[input[index3]] = input[input[index1]] * input[input[index2]]
}
