import com.emmav.adventofcode.IntcodeComputer
import java.io.File

fun main() {
    val memory = File("input/day5.txt")
        .readLines()
        .first()
        .split(",")
        .map { it.toInt() }
        .toTypedArray()

    val output = IntcodeComputer().compute(memory = memory, input = listOf(5))
    println("${output.joinToString(", ")}")
}
