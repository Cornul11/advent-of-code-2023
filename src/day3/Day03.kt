package day3

import println
import readInput


fun main() {
    fun isGearSymbol(c: Char): Boolean {
        return c == '*';
    }

    fun isSymbol(c: Char): Boolean {
        return !c.isDigit() && c != '.'
    }

    fun isOutOfBounds(input: String, i: Int, j: Int): Boolean {
        return i < 0 || i >= input.length || j < 0 || j >= input.length
    }

    fun extractNumber(input: String, idx: Int, j: Int): Pair<Int, Pair<Int, Int>> {
        var num = 0
        val horizontalStartIndex: Int

        var i = idx
        while (i >= 0 && input[i].isDigit()) {
            i--
        }

        horizontalStartIndex = i + 1
        i++

        while (i < input.length && input[i].isDigit()) {
            num = num * 10 + (input[i] - '0')
            i++
        }
        return Pair(num, Pair(horizontalStartIndex, j))
    }

    fun sumNumbersAround(
        input: List<String>, i: Int, j: Int, extractedIndices: MutableSet<Pair<Int, Int>>
    ): Int {
        var sum = 0
        for (di in -1..1) {
            for (dj in -1..1) {
                if (di == 0 && dj == 0) continue
                val ni = i + di
                val nj = j + dj
                if (isOutOfBounds(input[0], ni, nj)) continue
                val char = input[ni][nj]
                if (char.isDigit()) {
                    val (num, indices) = extractNumber(input[ni], nj, ni)
                    if (indices !in extractedIndices) {
                        extractedIndices.add(indices)
                        sum += num
                    }
                }
            }
        }
        return sum
    }

    fun sumNumbersAroundGears(
        input: List<String>, i: Int, j: Int
    ): Int {
        val extractedIndices: MutableSet<Pair<Int, Pair<Int, Int>>> = mutableSetOf()
        var product = 1
        for (di in -1..1) {
            for (dj in -1..1) {
                if (di == 0 && dj == 0) continue
                val ni = i + di
                val nj = j + dj
                if (isOutOfBounds(input[0], ni, nj)) continue
                val char = input[ni][nj]
                if (char.isDigit()) {
                    val numDef = extractNumber(input[ni], nj, ni)
                    if (numDef !in extractedIndices) {
                        extractedIndices.add(numDef)
                    }
                }
            }
        }
        if (extractedIndices.size == 2) {
            for (numDef in extractedIndices) {
                product *= numDef.first
            }
        } else {
            product = 0
        }
        return product
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        val extractedNumbers = mutableSetOf<Pair<Int, Int>>()
        for (i in input.indices) {
            for (j in input[i].indices) {
                val char = input[i][j]
                if (isSymbol(char)) {
                    sum += sumNumbersAround(input, i, j, extractedNumbers)
                }
            }
        }
        println(extractedNumbers.size)
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                val char = input[i][j]
                if (isGearSymbol(char)) {
                    sum += sumNumbersAroundGears(input, i, j)
                }
            }
        }
        return sum
    }

    val testInput1 = readInput(3, "test1")

    val result1 = part1(testInput1)
    result1.println()
    check(result1 == 4361)

    val result2 = part2(testInput1)
    result2.println()
    check(result2 == 467835)

    val input = readInput(3, "input")
    part1(input).println()
    part2(input).println()
}