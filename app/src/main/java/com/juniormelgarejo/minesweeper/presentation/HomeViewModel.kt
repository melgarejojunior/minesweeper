package com.juniormelgarejo.minesweeper.presentation

import androidx.lifecycle.*
import com.juniormelgarejo.minesweeper.domain.GameStatus
import com.juniormelgarejo.minesweeper.domain.MineSweeper
import com.juniormelgarejo.minesweeper.utils.Event

class HomeViewModel : LifecycleObserver, ViewModel() {

    val redrawOptionsMenu: LiveData<Event<Boolean>> get() = _redrawOptionsMenu
    private val _redrawOptionsMenu = MutableLiveData<Event<Boolean>>()

    val initMineSweeper: LiveData<MineSweeper> get() = _initMineSweeper
    private val _initMineSweeper = MutableLiveData<MineSweeper>()

    val restart: LiveData<Event<Boolean>> get() = _restart
    private val _restart = MutableLiveData<Event<Boolean>>()

    val endOfGame: LiveData<Boolean> get() = _endOfGame
    private val _endOfGame = MutableLiveData<Boolean>()

    private var currentMineSweeper: MineSweeper? = null

    internal fun onRestartClicked() {
        _restart.postValue(Event(true))
        onCreate()
    }

    internal fun onToggleButtonClicked() {
        _redrawOptionsMenu.postValue(Event(true))
    }

    internal fun updateGameStatus(status: GameStatus) {
        when (status) {
            GameStatus.GAME_OVER -> notifyGameOver()
            GameStatus.WINNER -> notifyWinner()
            else -> {
                // Nothing to do
            }
        }
    }

    private fun notifyWinner() {
        _endOfGame.postValue(true)
    }

    private fun notifyGameOver() {
        _endOfGame.postValue(false)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        currentMineSweeper = MineSweeper(13, 10, 15).also {
            _initMineSweeper.postValue(it)
        }

    }
}