package com.juniormelgarejo.minesweeper.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juniormelgarejo.minesweeper.databinding.ItemFieldBinding

class FieldViewHolder private constructor(private val binding: ItemFieldBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind() {

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