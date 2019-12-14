import com.emmav.adventofcode.IntcodeComputer
import java.io.File

val inputMemory = File("input/day7.txt")
    .readLines()
    .first()
    .split(",")
    .map { it.toInt() }
    .toTypedArray()

fun main() {
    val input = arrayOf(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)
    val memory = IntArray(1000).toTypedArray()
    input.copyInto(destination = memory)

    val output = IntcodeComputer(memory, mutableListOf(1)).compute()
    println("result: $output")
}
