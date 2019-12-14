import com.emmav.adventofcode.IntcodeComputer
import java.io.File

private val input = File("input/day11.txt")
    .readLines()
    .first()
    .split(",")
    .map { it.toLong() }
    .toTypedArray()

fun main() {
    // provide 0 if the robot is over a black panel or 1 if the robot is over a white panel

    IntcodeComputer(memory, mutableListOf(0)).compute()
}
