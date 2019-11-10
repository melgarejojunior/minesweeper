package com.juniormelgarejo.minesweeper.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juniormelgarejo.minesweeper.domain.Field

class MineAdapter : RecyclerView.Adapter<FieldViewHolder>() {


    val isFieldsVisible: Boolean
        get() = _isFieldsVisible

    private var _isFieldsVisible: Boolean = false
    private var items: List<Field> = emptyList()
    private val openedItems = mutableListOf<Field>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        return FieldViewHolder.inflate(parent) {
            openedItems.add(it)
            _isFieldsVisible
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        holder.bind(items[position], _isFieldsVisible)
    }

    internal fun setItems(items: List<Field>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun showAllFields() {
        _isFieldsVisible = _isFieldsVisible.not()
        items.forEach {
            if (it !in openedItems) notifyItemChanged(it.position)
        }
    }
}