package day4

import println
import readInput


fun main() {
    fun parseInput(input: List<String>): List<Pair<List<Int>, List<Int>>> {
        val cardsData = mutableListOf<Pair<List<Int>, List<Int>>>()
        for (line in input) {
            val cleanedLine = line.split(':').last().trim()
            val (winningInfo, playerInfo) = cleanedLine.split('|').map { it.trim() }
            val winningNumbers = winningInfo.split("\\s+".toRegex()).map { it.toInt() }
            val playerNumbers = playerInfo.split("\\s+".toRegex()).map { it.toInt() }
            cardsData.add(Pair(winningNumbers, playerNumbers))
        }
        return cardsData
    }

    fun part1(input: List<String>): Int {
        var totalScore = 0
        val cardsData = parseInput(input)
        for ((winningNumbers, playerNumbers) in cardsData) {
            var score = 0
            for (playerNumber in playerNumbers) {
                if (playerNumber in winningNumbers) {
                    if (score == 0) {
                        score += 1
                    } else {
                        score *= 2
                    }
                }
            }
            totalScore += score
        }
        return totalScore
    }

    fun part2(input: List<String>): Int {
        return -1
    }

    val testInput1 = readInput(4, "test1")

    val result1 = part1(testInput1)
    result1.println()
    check(result1 == 13)

//    val result2 = part2(testInput1)
//    result2.println()
//    check(result2 == 467835)

    val input = readInput(4, "input")
    part1(input).println()
//    part2(input).println()
}