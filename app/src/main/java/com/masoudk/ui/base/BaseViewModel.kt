package com.masoudk.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel : ViewModel() {


    private val viewModelJob = SupervisorJob()
    protected val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    protected val ioScope = CoroutineScope(viewModelJob + Dispatchers.IO)


    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}