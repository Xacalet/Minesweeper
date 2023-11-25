package com.xacalet.minesweeper.common.extensions

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

fun Context.vibrate() {
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    val duration = 100L
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator?.vibrate(
            VibrationEffect.createOneShot(
                duration,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    } else {
        vibrator?.vibrate(duration)
    }
}
