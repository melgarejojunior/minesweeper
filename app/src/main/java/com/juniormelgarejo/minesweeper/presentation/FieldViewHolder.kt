package com.juniormelgarejo.minesweeper.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juniormelgarejo.minesweeper.databinding.ItemFieldBinding
import com.juniormelgarejo.minesweeper.domain.Field
import com.juniormelgarejo.minesweeper.utils.showValue

class FieldViewHolder private constructor(
    private val binding: ItemFieldBinding,
    private val onClicked: (Field) -> Boolean
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(field: Field, visible: Boolean) {
        binding.field = field
        with(binding.cover) {
            showValue(visible)
            setOnClickListener { binding.cover.showValue(!onClicked(field)) }
        }
    }

    companion object {
        fun inflate(parent: ViewGroup, onClicked: (Field) -> Boolean): FieldViewHolder {
            return FieldViewHolder(
                ItemFieldBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClicked
            )
        }
    }
}