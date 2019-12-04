var min = 246540
var max = 787419

fun main() {
    val validNumbers = mutableListOf<Int>()
    for (x in min..max) {
        // print("checking: $x\n")
        if (isValid(x)) {
            validNumbers.add(x)
        }
    }
    print("count: ${validNumbers.size}\n\n")
}

fun isValid(number: Int): Boolean {
    var currentNumber = number
    val digitFrequencyMap = mutableMapOf<Int, Int>()

    while (true) {
        val currentDigit = currentNumber % 10
        val nextDigit = (currentNumber / 10) % 10
        if (!digitFrequencyMap.contains(currentDigit)) {
            digitFrequencyMap.put(currentDigit, 1)
        } else {
            digitFrequencyMap.put(currentDigit, digitFrequencyMap.get(currentDigit)!! + 1)
        }

        // Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
        if (nextDigit > currentDigit) {
            return false
        }

        currentNumber /= 10
        if (currentNumber <= 0) {
            break
        }
    }
    return digitFrequencyMap.values.filter { it == 2 }.count() >= 1
}
