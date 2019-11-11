package com.juniormelgarejo.minesweeper.utils

import android.content.Context
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.juniormelgarejo.minesweeper.R
import com.juniormelgarejo.minesweeper.domain.Field

object DataBindingUtils {

    @JvmStatic
    @BindingAdapter("bind:field")
    fun TextView.setField(field: Field) {
        with(field) {
            text = when {
                isBomb -> context.getString(R.string.bomb_marker)
                bombsAround == 0 -> ""
                else -> bombsAround.toString()
            }
            setTextColor(
                if (!isBomb) resolveColor(context, bombsAround)
                else context.colorCompat(android.R.color.black)
            )
        }
    }

    private fun resolveColor(
        context: Context,
        bombsAround: Int
    ): Int {
        return context.colorCompat(
            when (bombsAround) {
                1 -> android.R.color.holo_blue_dark
                2 -> android.R.color.holo_red_dark
                3 -> android.R.color.holo_green_dark
                4 -> android.R.color.holo_purple
                else -> android.R.color.holo_blue_bright
            }
        )
    }
}