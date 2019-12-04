fun main() {
    val result = calculateSteps(
        wire1 = listOf("R8", "U5", "L5", "D3"),
        wire2 = listOf("U7", "R6", "D4", "L4")
    )
    print("result: ${result}\n")
}

fun calculateSteps(wire1: List<String>, wire2: List<String>): Int {
    val coordWire1 = Coordinate(0, 0)
    val coordWire2 = Coordinate(0, 0)

    print("wire 1:\n")
    parseAndMove(wire1, coordWire1)
    // coordWire1.pointsVisited.forEach { print("visited: $it\n") }

    print("wire 2:\n")
    parseAndMove(wire2, coordWire2)
    // coordWire2.pointsVisited.forEach { print("visited: $it\n") }

    print("intersection:\n")
    val intersection =
        coordWire1.pointsVisited.map { it.first }.intersect(coordWire2.pointsVisited.map { it.first }).toMutableSet()
    intersection.remove(Coordinate(0, 0))
    intersection.forEach { print("$it\n") }

    // print("visited in 1:\n")
    // coordWire1.pointsVisited.forEach { print("visited: $it\n") }
    // print("visited in 2:\n")
    // coordWire2.pointsVisited.forEach { print("visited: $it\n") }

    val pointsFrom1 = coordWire1.pointsVisited.filter { intersection.contains(it.first) }
    val pointsFrom2 = coordWire2.pointsVisited.filter { intersection.contains(it.first) }

    print("1:\n")
    pointsFrom1.forEach { print("${it.first}, ${it.second}\n") }
    print("2:\n")
    pointsFrom2.forEach { print("${it.first}, ${it.second}\n") }

    val steps = mutableListOf<Int>()
    for (x in pointsFrom1) {
        val y = pointsFrom2.find { it.first == x.first }!!
        print("coord for ${x.first}, total: ${x.second + y.second}\n")
        steps.add(x.second + y.second)
    }
    steps.sort()

    return steps.first()

    // intersection.forEach { print("$it, abs distance: ${Math.abs(it.x) + Math.abs(it.y)}\n") }
    // val result = intersection.map { Math.abs(it.x) + Math.abs(it.y) }.toSortedSet().first()
    // print("result: $result\n")
}

fun parseAndMove(input: List<String>, coordinate: Coordinate) {
    input.map { toDirectionAndDistance(it) }
        .forEach { (direction, distance) -> coordinate.move(direction, distance) }
}

fun toDirectionAndDistance(input: String): Pair<Direction, Int> {
    val directionString = input.first()
    val distanceString = input.substring(startIndex = 1)
    val direction = when (directionString) {
        'U' -> Direction.UP
        'D' -> Direction.DOWN
        'L' -> Direction.LEFT
        'R' -> Direction.RIGHT
        else -> throw IllegalArgumentException("Unexpected direction: $directionString")
    }
    // print("direction: $direction, distance: ${distanceString.toInt()} \n")
    return Pair(direction, distanceString.toInt())
}

data class Coordinate(var x: Int, var y: Int) {
    val pointsVisited = mutableSetOf<Pair<Coordinate, Int>>()

    private var stepsTaken = 1

    fun move(direction: Direction, distance: Int) {
        print("move in direction: $direction, distance: $distance\n")
        var isFirstMove = true
        when (direction) {
            Direction.UP -> {
                for (i in 0..distance) {
                    pointsVisited.add(Pair(Coordinate(x + i, y), stepsTaken))
                    if (isFirstMove) {
                        isFirstMove = false
                    } else {
                        stepsTaken++
                    }
                    // print("up ${pointsVisited.last()}, stepsTaken: $stepsTaken\n")
                }
                x += distance
            }
            Direction.DOWN -> {
                for (i in 0..distance) {
                    pointsVisited.add(Pair(Coordinate(x - i, y), stepsTaken))
                    if (isFirstMove) {
                        isFirstMove = false
                    } else {
                        stepsTaken++
                    }
                    // print("down ${pointsVisited.last()}, stepsTaken: $stepsTaken\n")
                }
                x -= distance
            }
            Direction.LEFT -> {
                for (i in 0..distance) {
                    pointsVisited.add(Pair(Coordinate(x, y - i), stepsTaken))
                    if (isFirstMove) {
                        isFirstMove = false
                    } else {
                        stepsTaken++
                    }
                    // print("left ${pointsVisited.last()}, stepsTaken: $stepsTaken\n")
                }
                y -= distance
            }
            Direction.RIGHT -> {
                for (i in 0..distance) {
                    pointsVisited.add(Pair(Coordinate(x, y + i), stepsTaken))
                    if (isFirstMove) {
                        isFirstMove = false
                    } else {
                        stepsTaken++
                    }
                    // print("right ${pointsVisited.last()}, stepsTaken: $stepsTaken\n")
                }
                y += distance
            }
        }
        print("now at $this, stepsTaken: $stepsTaken\n")
        isFirstMove = true
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}

enum class Direction { UP, DOWN, LEFT, RIGHT }
