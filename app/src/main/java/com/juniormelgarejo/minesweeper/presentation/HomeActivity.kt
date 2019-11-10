package com.juniormelgarejo.minesweeper.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juniormelgarejo.minesweeper.R
import com.juniormelgarejo.minesweeper.databinding.ActivityHomeBinding
import com.juniormelgarejo.minesweeper.domain.MineSweeper
import com.juniormelgarejo.minesweeper.utils.consume
import com.juniormelgarejo.minesweeper.utils.observeEvent
import com.juniormelgarejo.minesweeper.utils.safeIf

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var mineAdapter: MineAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVars()
        setupToolbar()
        setupView()
        subscribeUi()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(resolveMenuRes(), menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.restart_menu -> consume { viewModel.onRestartClicked() }
            R.id.show_hide_toggle_menu -> consume { viewModel.onToggleButtonClicked() }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initVars() {
        if (!::binding.isInitialized)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        if (!::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
            lifecycle.addObserver(viewModel)
        }
    }

    private fun subscribeUi() {
        viewModel.redrawOptionsMenu.observeEvent(this, ::onNextRedrawOptions)
    }

    private fun setupView() {
        if (mineAdapter == null) {
            mineAdapter = MineAdapter()
        }
        with(binding.recyclerView) {
            if (adapter == null) adapter = mineAdapter
            layoutManager = GridLayoutManager(context, 10)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
            })
        }
        mineAdapter?.setItems(MineSweeper(13, 10, 15).fields)
    }

    private fun onNextRedrawOptions(shouldRedraw: Boolean?) {
        safeIf(shouldRedraw) {
            mineAdapter?.showAllFields()
            invalidateOptionsMenu()
        }
    }

    private fun resolveMenuRes(): Int {
        return if (mineAdapter?.isFieldsVisible == true)
            R.menu.menu_home_invisible
        else
            R.menu.menu_home_visible
    }

    private fun setupToolbar() {
        binding.toolbar.title = getString(R.string.app_name)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
