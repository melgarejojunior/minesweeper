package com.juniormelgarejo.minesweeper.domain

data class Field(
    val position: Int,
    val row: Int,
    val column: Int,
    val isBomb: Boolean
) {

    fun setNumOfBombsAround(listOfBombs: Set<Int>) {

    }

    companion object {
        fun fromAbsolutePosition(
            position: Int,
            numOfRows: Int,
            isBomb: Boolean
        ): Field {
            return Field(
                position = position,
                row = position / numOfRows,
                column = position % numOfRows,
                isBomb = isBomb
            )
        }
    }
}