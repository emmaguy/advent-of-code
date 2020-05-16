package com.emmav.adventofcode.`2015`

import java.math.BigInteger
import java.security.MessageDigest

fun main() {
    println("part1: ${findLowestNumberWhichStartsWithAtLeastFiveZeros("yzbqklnj")}")
    println("part2: ${findLowestNumberWhichStartsWithAtLeastSixZeros("yzbqklnj")}")
}

internal fun findLowestNumberWhichStartsWithAtLeastFiveZeros(key: String): Int {
    var number = 0
    while (true) {
        val hash = calculateSecretKeyHash(key, number)
        if (hash.substring(0, 5) == "00000") {
            return number
        }
        number++
    }
}

internal fun findLowestNumberWhichStartsWithAtLeastSixZeros(key: String): Int {
    var number = 0
    while (true) {
        val hash = calculateSecretKeyHash(key, number)
        if (hash.substring(0, 6) == "000000") {
            return number
        }
        number++
    }
}

internal fun calculateSecretKeyHash(key: String, number: Int): String {
    return "$key$number".md5()
}

internal fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}