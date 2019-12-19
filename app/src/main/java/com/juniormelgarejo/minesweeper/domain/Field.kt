package com.juniormelgarejo.minesweeper.domain

/**
 * A classe [Field] representa cada quadrado dentro do campo minado e guarda informações,
 * como por exemplo, posição linear (absoluta), linha e coluna (relativa) e se o quadrado é bomba e
 * quantas bombas têm ao redor de si.
 * Além de comseguir retornar o pares relativos aos quadrados adjacentes.
 * */
data class Field private constructor(
    val position: Int,
    val row: Int,
    val column: Int,
    val isBomb: Boolean,
    var bombsAround: Int = 0
) {

    fun getBorders(): List<Pair<Int, Int>> {
        val pairs = mutableListOf<Pair<Int, Int>>()
        for (i in row.bombRange()) {
            for (j in column.bombRange()) {
                if ((i >= 0 && j >= 0)) pairs.add(i to j)
            }
        }
        return pairs
    }

    private fun setNumOfBombsAround(listOfBombs: List<Int>, numOfColumns: Int) {
        if (isBomb) return
        listOfBombs.forEach { bombPos ->
            val bombRow = bombPos / numOfColumns
            val bombColumn = bombPos % numOfColumns
            if (bombRow in row.bombRange() && bombColumn in column.bombRange()) bombsAround++
        }
    }

    private fun Int.bombRange() = this.dec()..this.inc()

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