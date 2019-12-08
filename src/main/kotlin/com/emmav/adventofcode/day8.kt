package com.emmav.adventofcode

import java.io.File

private const val imageData = "123456789012"
private const val imageWidth = 3
private const val imageHeight = 2

fun main() {
    val input = File("input/day8.txt").readLines().first()
    val layers = parseImage(input, 25, 6)

    val (count, index, result) = layers.mapIndexed { index, list ->
        val count = list.count { it == "0" }
        Triple(count, index, list.count { it == "1" } * list.count { it == "2" })
    }.sortedBy { it.first }.first()

    println("with most 0, index: $index with $count, result: $result")

//    layers.forEach { println("layer: ${it.joinToString(", ")}") }
}

fun parseImage(imageData: String, imageWidth: Int, imageHeight: Int): List<List<String>> {
    val numberOfLayers = imageData.length / (imageWidth * imageHeight)
    val layers = mutableListOf<List<String>>()
    var index = 0
    for (layer in 0 until numberOfLayers) {
        val list = mutableListOf<String>()
        for (width in 0 until imageWidth) {
            for (height in 0 until imageHeight) {
                list.add(imageData[index].toString())
//                println("pixel width/height: $width, $height layerIndex: $layer. index: $index")
                index++
            }
        }
        layers.add(list)
    }

    return layers
}
