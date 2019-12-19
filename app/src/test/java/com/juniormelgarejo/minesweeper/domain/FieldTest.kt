package com.juniormelgarejo.minesweeper.domain

import org.junit.Test

class FieldTest {

    private val numOfBombs = 3
    private val numOfRows = 4
    private val numOfColumns = 4
    private val bombList = listOf(1, 14, 15)

    @Test
    fun getBorders() {
        //Given
        val field = Field.fromAbsolutePosition(11, 4, false, bombList)
        //When
        val result = field.getBorders().toSet()
        //Then
        val assertionResult = setOf(
            1 to 2,
            1 to 3,
            2 to 2,
            3 to 2,
            3 to 3
        )
        assert(result.containsAll(assertionResult) && assertionResult.containsAll(result))
    }

    @Test
    fun getPosition() {
    }

    @Test
    fun getBombsAround() {
    }

    @Test
    fun setBombsAround() {
    }
}