import com.emmav.adventofcode.IntcodeComputer
import java.io.File

val inputMemory = File("input/day9.txt")
    .readLines()
    .first()
    .split(",")
    .map { it.toLong() }
    .toTypedArray()

fun main() {
    val input = arrayOf(104, 1125899906842624, 99)
    val memory = LongArray(100000000).toTypedArray()
    inputMemory.copyInto(destination = memory)

    IntcodeComputer(memory, mutableListOf(1)).compute()
    IntcodeComputer(memory, mutableListOf(2)).compute()
}
