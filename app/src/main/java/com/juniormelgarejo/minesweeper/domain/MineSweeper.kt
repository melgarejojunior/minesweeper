package com.juniormelgarejo.minesweeper.domain

import java.util.*
import kotlin.random.Random

data class MineSweeper(
    private val rows: Int,
    private val columns: Int,
    private val bombs: Int
) {
    private val numOfFields = rows * columns
    private val fields = mutableListOf<Field>()

    init {
        val bombsPositions = createBombs()
        for (pos in 0..numOfFields) {
            fields.add(
                Field.fromAbsolutePosition(pos, rows, bombsPositions.contains(pos)).apply {
                    setNumOfBombsAround(bombsPositions)
                }
            )
        }
    }

    private fun createBombs(): MutableSet<Int> {
        val bombsSet = mutableSetOf<Int>()
        val randomSeed = Random(Date().time)
        repeat(bombs) {
            var possiblePos = randomSeed.nextInt(numOfFields - 1)
            while (bombsSet.contains(possiblePos)) possiblePos =
                Random(Date().time).nextInt(numOfFields - 1)
            bombsSet.add(possiblePos)
        }
        return bombsSet
    }
}