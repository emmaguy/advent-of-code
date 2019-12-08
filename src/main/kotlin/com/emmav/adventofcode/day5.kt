import com.emmav.adventofcode.IntcodeComputer
import java.io.File

fun main() {
    val memory = File("input/day5.txt")
        .readLines()
        .first()
        .split(",")
        .map { it.toInt() }
        .toTypedArray()

    IntcodeComputer().compute(memory = memory)
}
