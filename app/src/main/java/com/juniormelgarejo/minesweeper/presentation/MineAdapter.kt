package com.juniormelgarejo.minesweeper.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juniormelgarejo.minesweeper.domain.Field
import com.juniormelgarejo.minesweeper.domain.GameStatus

class MineAdapter(
    private val onFirstItemClicked: (Int) -> Unit,
    private val updateGameStatus: (GameStatus) -> Unit
) : RecyclerView.Adapter<FieldViewHolder>() {


    val isFieldsVisible: Boolean
        get() = _isFieldsVisible

    private var _isFieldsVisible: Boolean = false
    private var items: List<Field> = emptyList()
    private var openedItems = mutableListOf<Field>()

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

    internal fun setItems(items: List<Field>) {
        this.items = items
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
        this.openedItems = mutableListOf()
    }


    private fun resolveOnItemClicked(it: Field): Boolean {
        openedItems.add(it)
        if (openedItems.size == 1) onFirstItemClicked(it.position)
        else openAdjacentFields(it)
        gameStatus(it)
        return _isFieldsVisible
    }

    private fun gameStatus(field: Field) {
        updateGameStatus(
            when {
                field.isBomb -> GameStatus.GAME_OVER
                openedItems.size == 15 -> GameStatus.WINNER
                else -> GameStatus.ON_GOING
            }
        )
    }

    private fun openAdjacentFields(field: Field) {
        field.getAdjacents().forEach { pair ->
            items.find { it.row == pair.first && it.column == pair.second }?.let {
                if (it in openedItems) return
                if (!it.isBomb && it.bombsAround == 0) {
                    openedItems.add(it)
                    notifyItemChanged(it.position)
                }
            }
        }
    }
}