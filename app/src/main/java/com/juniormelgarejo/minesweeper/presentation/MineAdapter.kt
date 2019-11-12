package com.juniormelgarejo.minesweeper.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juniormelgarejo.minesweeper.domain.Field
import com.juniormelgarejo.minesweeper.domain.GameStatus
import com.juniormelgarejo.minesweeper.domain.MineSweeper

class MineAdapter(
    private val updateGameStatus: (GameStatus) -> Unit
) : RecyclerView.Adapter<FieldViewHolder>() {


    val isFieldsVisible: Boolean
        get() = _isFieldsVisible

    private var _isFieldsVisible: Boolean = false
    private var items: List<Field> = emptyList()
    private var openedItems = mutableSetOf<Field>()
    private var mineSweeper: MineSweeper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        return FieldViewHolder.inflate(parent, ::resolveOnItemClicked)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        holder.bind(
            items[position],
            _isFieldsVisible || openedItems.find { it.position == position } != null
        )
    }

    internal fun setItems(mineSweeper: MineSweeper) {
        this.mineSweeper = mineSweeper
        this.items = mineSweeper.placeholder()
        notifyDataSetChanged()
    }

    internal fun showAllFields() {
        _isFieldsVisible = _isFieldsVisible.not()
        items.forEach {
            if (it !in openedItems) notifyItemChanged(it.position)
        }
    }

    internal fun restart() {
        this.items = emptyList()
        this.openedItems = mutableSetOf()
    }


    private fun resolveOnItemClicked(field: Field): Boolean {
        if (openedItems.size == 0) populate(field)
        else {
            openedItems.add(field)
            if (items[field.position].bombsAround == 0) openAdjacentFields(field)
            gameStatus(field)
        }
        return _isFieldsVisible
    }

    private fun populate(field: Field) {
        mineSweeper?.create(field.position)
        mineSweeper?.fields?.let {
            this.items = it
            openedItems.add(it[field.position])
            notifyDataSetChanged()
        }
    }

    private fun gameStatus(field: Field) {
        updateGameStatus(
            when {
                field.isBomb -> GameStatus.GAME_OVER
                items.size - openedItems.size == mineSweeper?.numOfBombs -> GameStatus.WINNER
                else -> GameStatus.ON_GOING
            }
        )
    }

    private fun openAdjacentFields(field: Field) {
        field.getBorders().forEach { pair ->
            items.findByRelativePosition(pair)?.let { field ->
                if (openedItems.findByRelativePosition(pair) == null && !field.isBomb && field.bombsAround == 0) {
                    openedItems.add(field)
                    notifyItemChanged(field.position)
                    resolveOnItemClicked(field)
                }
            }
        }
    }

    private fun Collection<Field>.findByRelativePosition(pair: Pair<Int, Int>): Field? {
        return find { it.row == pair.first && it.column == pair.second }
    }
}