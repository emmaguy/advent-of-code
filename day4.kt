var min = 246540
var max = 787419

fun main() {
    val validNumbers = mutableListOf<Int>()
    for (x in min..max) {
        print("checking: $x\n")
        if (isValid(x)) {
            validNumbers.add(x)
        }
    }
    print("count: ${validNumbers.size}\n\n")
}

fun isValid(number: Int): Boolean {
    var currentNumber = number
    var lastDigit = -1
    var hasTwoAdjacentDigits = false
    // Two adjacent digits are the same (like 22 in 122345).

    while (true) {
        val currentDigit = currentNumber % 10
        val nextDigit = (currentNumber / 10) % 10
        // print("digit: $currentDigit, next: $nextDigit\n")

        // Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
        if(nextDigit > currentDigit) {
            // print("next too large, returning digit: $currentDigit, next: $nextDigit\n")
            return false
        }

        if(lastDigit != -1 && lastDigit == currentDigit) {
            // print("has two adjacent digits: $lastDigit\n")
            hasTwoAdjacentDigits = true
        }
        
        lastDigit = currentDigit
        currentNumber /= 10
        if (currentNumber <= 0) {
            break
        }
    }
    return hasTwoAdjacentDigits
}
