package com.juniormelgarejo.minesweeper.domain

import kotlin.random.Random

/**
 * Principal entidade do programa
 * Guarda os dados sobre quantidade de linhas e colunas, e quantidade de bombas
 * A partir disso, essa classe usa o método [placeholder] para criar quadrados vazios e esperar o primeiro clique
 * Após o primeiro clique o método [create] que espera a posição desse clique, é invocado e gera as
 * bombas aleatoriamente, preenchendo assim o campo minado.
 * */

data class MineSweeper(
    private val rows: Int,
    private val columns: Int,
    val numOfBombs: Int
) {
    val fields: List<Field> get() = _fields

    private val numOfFields = (rows * columns)
    private val _fields = mutableListOf<Field>()
    private var firstPlayed: Int = -1

    init {
        check(numOfBombs < numOfFields) { "Number of bombs should not be greater or equals to row * column" }
    }

    internal fun create(firstPlayed: Int) {
        this.firstPlayed = firstPlayed
        val bombsPositions = createBombs()
        populate(bombsPositions)

    }

    internal fun placeholder(): List<Field> {
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