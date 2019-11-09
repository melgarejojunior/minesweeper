package com.juniormelgarejo.minesweeper.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juniormelgarejo.minesweeper.databinding.ItemFieldBinding
import com.juniormelgarejo.minesweeper.utils.showValue

class FieldViewHolder private constructor(
    private val binding: ItemFieldBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    private var visible = true


    fun bind(_isFieldsVisible: Boolean) {
        with(binding) {
            cover.showValue(_isFieldsVisible)
        }
    }

    companion object {
        fun inflate(parent: ViewGroup): FieldViewHolder {
            return FieldViewHolder(
                ItemFieldBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}