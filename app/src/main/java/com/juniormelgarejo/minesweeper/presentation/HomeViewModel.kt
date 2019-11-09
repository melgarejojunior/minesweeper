package com.juniormelgarejo.minesweeper.presentation

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juniormelgarejo.minesweeper.utils.Event

class HomeViewModel : LifecycleObserver, ViewModel() {

    val redrawOptionsMenu: LiveData<Event<Boolean>> get() = _redrawOptionsMenu
    private val _redrawOptionsMenu = MutableLiveData<Event<Boolean>>()

    fun onRestartClicked() {

    }

    fun onToggleButtonClicked() {
        _redrawOptionsMenu.postValue(Event(true))
    }
}