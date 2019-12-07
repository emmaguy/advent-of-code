import java.io.File

var total = 0

fun main() {
    val input = File("input/day6input.txt").readLines()

    val map = mutableMapOf<String, Object>()

    input.forEach {
        // COM)B = B is in Orbit around COM
        val objects = it.split(")")
        val objectBeingOrbited = getOrCreate(map, objects.first())
        val objectDirectlyOrbiting = getOrCreate(map, objects.last())
        objectBeingOrbited.orbitedBy.add(objectDirectlyOrbiting)
    }

    val root = map["COM"]!!
    visit(root, 0)
    print("total: $total\n")
}

fun getOrCreate(map: MutableMap<String, Object>, objectName: String): Object {
    if (!map.containsKey(objectName)) {
        map[objectName] = Object(objectName)
    }
    return map[objectName]!!
}

fun visit(obj: Object, level: Int): Int {
    total += level
    return obj.orbitedBy.map { visit(it, level + 1) }.sum()
}

data class Object(val name: String) {
    val orbitedBy = mutableListOf<Object>()
}
