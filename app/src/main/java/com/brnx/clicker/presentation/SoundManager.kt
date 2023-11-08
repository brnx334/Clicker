package com.brnx.clicker.presentation

import android.content.Context
import android.media.MediaPlayer

class SoundManager {
    private var mediaPlayer: MediaPlayer? = null

    fun playSound(resourceId: Int, context: Context) {
        stopSound()

        mediaPlayer = MediaPlayer.create(context, resourceId)
        mediaPlayer?.setVolume(0.5F, 0.5F)
        mediaPlayer?.start()
    }

    private fun stopSound() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}