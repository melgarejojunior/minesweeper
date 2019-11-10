package com.juniormelgarejo.minesweeper.domain

import kotlin.random.Random

data class MineSweeper(
    private val rows: Int,
    private val columns: Int,
    private val numOfBombs: Int
) {
    val fields: List<Field> get() = _fields

    private val numOfFields = (rows * columns)
    private val _fields = mutableListOf<Field>()

    init {
        check(numOfBombs < numOfFields) { "Number of bombs should not be greater or equals to row * column" }
        val bombsPositions = createBombs()
        populate(bombsPositions)
    }

    private fun populate(bombsPositions: List<Int>) {
        for (pos in 0 until numOfFields) {
            _fields.add(
                Field.fromAbsolutePosition(
                    pos,
                    columns,
                    bombsPositions.contains(pos),
                    bombsPositions
                )
            )
        }
    }

    private fun createBombs(): List<Int> {
        val bombsSet = mutableSetOf<Int>()
        while (bombsSet.size != numOfBombs)
            bombsSet.add(Random.nextInt(numOfFields))
        return bombsSet.sorted()
    }
}