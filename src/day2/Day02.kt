package day2

import println
import readInput

fun main() {
    val maxCubes = mapOf("red" to 12, "green" to 13, "blue" to 14)


    fun parseInput(input: String): Array<MutableMap<String, Int>> {
        val setsColors: ArrayList<MutableMap<String, Int>> = arrayListOf()
        val sets = input.split(";").toMutableList()
        sets[0] = sets[0].substring(sets[0].indexOf(":") + 1)
        for (i in sets.indices) {
            val colorMap = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
            val game = sets[i]
            val subsets = game.split(",")
            for (j in subsets.indices) {
                val subset = subsets[j].trim()
                val color = subset.substring(subset.indexOf(" ") + 1)
                val num = subset.substring(0, subset.indexOf(" ")).toInt()
                colorMap[color] = colorMap[color]!! + num
            }
            setsColors.add(colorMap)
        }
        return setsColors.toTypedArray()
    }


    fun isGamePossible(input: String): Boolean {
        val result = parseInput(input)
        for (i in result.indices) {
            val colorMap = result[i]
            for (color in colorMap.keys) {
                if (colorMap[color]!! > maxCubes[color]!!) {
                    return false
                }
            }
        }
        return true
    }

    fun extractMinimums(input: String): MutableMap<String, Int> {
        val result = parseInput(input)
        val minMap = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        for (i in result.indices) {
            val colorMap = result[i]
            for (color in colorMap.keys) {
                if (colorMap[color]!! > minMap[color]!!) {
                    minMap[color] = colorMap[color]!!
                }
            }
        }
        return minMap
    }

    fun part1(input: List<String>): Int {
        var sumIds = 0
        for (line in input) {
            if (isGamePossible(line)) {
                sumIds += input.indexOf(line) + 1
            }
        }
        return sumIds
    }

    fun computePower(minMap: MutableMap<String, Int>): Int {
        var power = 1
        for (color in minMap.keys) {
            power *= minMap[color]!!
        }
        return power
    }

    fun part2(input: List<String>): Int {
        var sumPower = 0
        for (line in input) {
            val minMap = extractMinimums(line)
            sumPower += computePower(minMap)
        }
        return sumPower
    }

    val testInput1 = readInput(2, "test1")

    val result1 = part1(testInput1)
    result1.println()
    check(result1 == 8)

    val result2 = part2(testInput1)
    result2.println()
    check(result2 == 2286)

    val input = readInput(2, "input")
    part1(input).println()
    part2(input).println()
}