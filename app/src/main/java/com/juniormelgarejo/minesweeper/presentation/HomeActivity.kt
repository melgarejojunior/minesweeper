package com.juniormelgarejo.minesweeper.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.juniormelgarejo.minesweeper.R

class HomeActivity : AppCompatActivity() {


    private lateinit var viewModel: HomeViewModel
    private var mineAdapter: MineAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
