package com.example.volume_control

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager

class VolumeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "VOL_UP", "VOL_DOWN" -> {
                val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                val dir = if (intent.action == "VOL_UP") AudioManager.ADJUST_RAISE else AudioManager.ADJUST_LOWER
                am.adjustStreamVolume(AudioManager.STREAM_MUSIC, dir, AudioManager.FLAG_SHOW_UI)
            }
            "NOTIF_DISMISSED", Intent.ACTION_BOOT_COMPLETED -> VolumeNotification.show(context)
        }
    }
}