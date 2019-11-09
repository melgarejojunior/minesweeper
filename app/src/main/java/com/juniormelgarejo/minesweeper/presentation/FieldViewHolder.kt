package com.juniormelgarejo.minesweeper.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.juniormelgarejo.minesweeper.databinding.ItemFieldBinding

class FieldViewHolder private constructor(private val binding: ItemFieldBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var visible = true


    fun bind() {
        with(binding) {
            cover.setOnClickListener {
                cover.toggleVisibility()
            }
        }
    }

    private fun View.toggleVisibility() {
        backgroundTintList =
            ColorStateList.valueOf(
                context.colorCompat(
                    if (visible) android.R.color.transparent else android.R.color.darker_gray
                )
            )
        visible = !visible
    }

    private fun Context.colorCompat(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)


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