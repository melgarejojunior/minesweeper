package com.juniormelgarejo.minesweeper.domain

import org.junit.Test

class FieldTest {

    private val numOfColumns = 4
    private val bombList = listOf(1, 14, 15)

    @Test
    fun positionCreation() {
        //Given
        val position = 6
        val field = Field.fromAbsolutePosition(position, numOfColumns, bombList.contains(position), bombList)
        //When
        val resultColumn = field.column
        val resultRow = field.row
        val resultIsBomb = field.isBomb
        //Then
        assert(resultColumn == 2 && resultRow == 1 && !resultIsBomb)
    }

    @Test
    fun getBorders() {
        //Given
        val position = 8
        val field = Field.fromAbsolutePosition(position, numOfColumns, bombList.contains(position), bombList)
        //When
        val result = field.getBorders().toSet()
        //Then
        val assertionResult = setOf(
            1 to 0,
            1 to 1,
            2 to 1,
            3 to 0,
            3 to 1
        )
        assert(result.containsAll(assertionResult) && assertionResult.containsAll(result))
    }

    @Test
    fun getPosition() {
        // Given
        val position = 8
        val field = Field.fromAbsolutePosition(position, numOfColumns, bombList.contains(position), bombList)
        // When
        val result = field.position
        // Then
        assert(result == 8)
    }

    @Test
    fun getBombsAround() {
        // Given
        val position = 10
        val field = Field.fromAbsolutePosition(position, numOfColumns, bombList.contains(position), bombList)
        // When
        val result = field.bombsAround
        // Then
        assert(result == 2)
    }
}