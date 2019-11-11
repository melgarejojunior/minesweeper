package com.juniormelgarejo.minesweeper.presentation

import androidx.lifecycle.*
import com.juniormelgarejo.minesweeper.domain.Field
import com.juniormelgarejo.minesweeper.domain.GameStatus
import com.juniormelgarejo.minesweeper.domain.MineSweeper
import com.juniormelgarejo.minesweeper.utils.Event

class HomeViewModel : LifecycleObserver, ViewModel() {

    val redrawOptionsMenu: LiveData<Event<Boolean>> get() = _redrawOptionsMenu
    private val _redrawOptionsMenu = MutableLiveData<Event<Boolean>>()

    val initMineSweeper: LiveData<List<Field>> get() = _initMineSweeper
    private val _initMineSweeper = MutableLiveData<List<Field>>()

    val restart: LiveData<Event<Boolean>> get() = _restart
    private val _restart = MutableLiveData<Event<Boolean>>()

    var currentMineSweeper: MineSweeper? = null

    internal fun onRestartClicked() {
        _restart.postValue(Event(true))
        onCreate()
    }

    internal fun onToggleButtonClicked() {
        _redrawOptionsMenu.postValue(Event(true))
    }

    internal fun onFirstItemClicked(position: Int) {
        currentMineSweeper?.create(position)
        _initMineSweeper.postValue(currentMineSweeper?.fields)
    }

    internal fun updateGameStatus(status: GameStatus) {
        when(status) {
            GameStatus.GAME_OVER -> notifyGameOver()
            GameStatus.WINNER -> notifyWinner()
        }
    }

    private fun notifyWinner() {

    }

    private fun notifyGameOver() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        currentMineSweeper = MineSweeper(13, 10, 15).also {
            _initMineSweeper.postValue(
                it.placeholder()
            )
        }

    }
}