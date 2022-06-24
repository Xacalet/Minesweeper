package com.xacalet.minesweeper.common.engine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

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
