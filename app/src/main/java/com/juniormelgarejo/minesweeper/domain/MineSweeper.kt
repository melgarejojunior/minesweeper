package com.juniormelgarejo.minesweeper.domain

import kotlin.random.Random

data class MineSweeper(
    private val rows: Int,
    private val columns: Int,
    val numOfBombs: Int,
    private val empty: Boolean = false
) {
    val fields: List<Field> get() = _fields

    private val numOfFields = (rows * columns)
    private val _fields = mutableListOf<Field>()
    private var firstPlayed: Int = -1

    init {
        check(numOfBombs < numOfFields) { "Number of bombs should not be greater or equals to row * column" }
    }

    fun create(firstPlayed: Int) {
        this.firstPlayed = firstPlayed
        val bombsPositions = createBombs()
        populate(bombsPositions)

    }

    fun placeholder(): List<Field> {
        return mutableListOf<Field>().apply {
            repeat(numOfFields) {
                add(Field.empty(it))
            }
        }
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
            Random.nextInt(numOfFields).run { if (this != firstPlayed) bombsSet.add(this) }
        return bombsSet.sorted()
    }

}