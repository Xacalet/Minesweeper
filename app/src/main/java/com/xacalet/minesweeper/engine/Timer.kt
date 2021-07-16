package com.xacalet.minesweeper.engine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class Timer {

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _seconds = MutableStateFlow(0)
    val seconds: StateFlow<Int> = _seconds

    private val ticker: Flow<Unit> = flow {
        while (true) {
            emit(Unit)
        }
    }.onEach { delay(1000) }

    fun start() {
        coroutineScope.launch {
            ticker.collect { _seconds.value += 1 }
        }
    }

    fun stop() {
        coroutineScope.coroutineContext.job.cancelChildren()
    }
}
