package com.example.volume_control

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon

object VolumeNotification {
    fun show(context: Context) {
        val nm = context.getSystemService(NotificationManager::class.java)
        nm.createNotificationChannel(
            NotificationChannel("vol", "Volume controls", NotificationManager.IMPORTANCE_LOW)
        )

        fun broadcast(act: String): PendingIntent = PendingIntent.getBroadcast(
            context, act.hashCode(),
            Intent(context, VolumeReceiver::class.java).setAction(act),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        fun action(label: String, act: String): Notification.Action =
            Notification.Action.Builder(
                Icon.createWithResource(context, R.drawable.ic_volume), label, broadcast(act)
            ).build()

        val open = PendingIntent.getActivity(
            context, 0,
            Intent(context, VolumeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
            PendingIntent.FLAG_IMMUTABLE
        )

        val n = Notification.Builder(context, "vol")
            .setSmallIcon(R.drawable.ic_volume)
            .setContentTitle("Volume")
            .setContentText("Tap for sliders")
            .setContentIntent(open)
            .setDeleteIntent(broadcast("NOTIF_DISMISSED"))
            .setOngoing(true)
            .addAction(action("Vol −", "VOL_DOWN"))
            .addAction(action("Vol +", "VOL_UP"))
            .build()
        nm.notify(1, n)
    }
}