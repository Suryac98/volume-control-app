package com.example.volume_control

import android.content.Intent
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class VolumeTileService : TileService() {
    override fun onStartListening() {
        qsTile?.apply {
            state = Tile.STATE_INACTIVE
            label = "Volume"
            updateTile()
        }
    }

    @Suppress("DEPRECATION")
    override fun onClick() {
        startActivityAndCollapse(
            Intent(this, VolumeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}