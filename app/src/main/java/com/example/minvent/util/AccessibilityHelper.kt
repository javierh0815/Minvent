package com.example.minvent.util

import android.annotation.SuppressLint
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

@SuppressLint("MissingPermission")
private fun obtenerVibrador(context: Context): Vibrator {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
}


fun playSuccessFeedback(context: Context) {
    try {
        val notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        RingtoneManager.getRingtone(context, notificationUri)?.play()

        val vibrator = obtenerVibrador(context)
        val effect = VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(effect)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun playErrorFeedback(context: Context) {
    try {
        val vibrator = obtenerVibrador(context)
        val timings = longArrayOf(0, 100, 80, 100)
        val amplitudes = intArrayOf(0, 255, 0, 255)

        val effect = VibrationEffect.createWaveform(timings, amplitudes, -1)
        vibrator.vibrate(effect)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}