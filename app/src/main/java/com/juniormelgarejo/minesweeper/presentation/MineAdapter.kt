package com.juniormelgarejo.minesweeper.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MineAdapter : RecyclerView.Adapter<FieldViewHolder>() {


    val isFieldsVisible: Boolean
        get() = _isFieldsVisible

    private var _isFieldsVisible: Boolean = false
    private var items: List<Int> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        return FieldViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        holder.bind(_isFieldsVisible)
    }

    internal fun setItems(items: List<Int>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun showAllFields() {
        _isFieldsVisible = _isFieldsVisible.not()
        notifyDataSetChanged()
    }
}