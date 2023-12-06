package day1

import println
import readInput

fun main() {

    fun getDigitPartOne(s: String, idx: Int): Int {
        if (s[idx].isDigit()) {
            return s[idx] - '0'
        }
        return -1
    }

    fun getDigitPartTwo(s: String, idx: Int): Int {
        if (s[idx].isDigit()) {
            return s[idx] - '0'
        }
        val words = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        for (i in words.indices) {
            val word = words[i]
            if (s.length - idx >= word.length && s.substring(idx, idx + word.length) == word) {
                return i + 1
            }
        }
        return -1
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            var a = -1
            var b = -1
            for (i in line.indices) {
                val an = getDigitPartOne(line, i)
                val bn = getDigitPartOne(line, line.length - i - 1)
                if (a == -1 && an != -1) a = an
                if (b == -1 && bn != -1) b = bn
                if (a != -1 && b != -1) break
            }
            sum += a * 10 + b
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            var a = -1
            var b = -1
            for (i in line.indices) {
                val an = getDigitPartTwo(line, i)
                val bn = getDigitPartTwo(line, line.length - i - 1)
                if (a == -1 && an != -1) a = an
                if (b == -1 && bn != -1) b = bn
                if (a != -1 && b != -1) break
            }
            sum += a * 10 + b
        }
        return sum
    }

    val testInput1 = readInput(1, "test1")
    check(part1(testInput1) == 142)

    val testInput2 = readInput(1, "test2")
    check(part2(testInput2) == 281)

    val input = readInput(1, "input")
    part1(input).println()
    part2(input).println()
}
