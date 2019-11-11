package com.juniormelgarejo.minesweeper.domain

data class Field(
    val position: Int,
    val row: Int,
    val column: Int,
    val isBomb: Boolean,
    var bombsAround: Int = 0
) {

    private fun setNumOfBombsAround(listOfBombs: List<Int>, numOfColumns: Int) {
        if (isBomb) return
        listOfBombs.forEach { bombPos ->
            val bombRow = bombPos / numOfColumns
            val bombColumn = bombPos % numOfColumns
            if (bombRow in row.bombRange() && bombColumn in column.bombRange()) bombsAround++
        }
    }

    private fun Int.bombRange() = this.dec()..this.inc()

    fun getAdjacents(): List<Pair<Int, Int>> {
        val pairs = mutableListOf<Pair<Int, Int>>()
        for (i in row.bombRange()) {
            for (j in row.bombRange()) {
                if (i >= 0 && j >= 0) pairs.add(i to j)
            }
        }
        return pairs
    }

    companion object {
        fun fromAbsolutePosition(
            position: Int,
            numOfColumns: Int,
            isBomb: Boolean,
            bombsSet: List<Int>
        ): Field {
            return Field(
                position = position,
                row = position / numOfColumns,
                column = position % numOfColumns,
                isBomb = isBomb
            ).apply { setNumOfBombsAround(bombsSet, numOfColumns) }
        }

        fun empty(position: Int): Field {
            return Field(position, 0, 0, false)
        }
    }
}