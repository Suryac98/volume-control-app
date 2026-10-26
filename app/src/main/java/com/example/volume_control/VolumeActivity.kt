package com.example.volume_control

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.media.AudioManager
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

class VolumeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.TOP)

        val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val density = resources.displayMetrics.density
        fun px(v: Int): Int = (v * density).toInt()

        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(px(20), px(16), px(20), px(24))
            background = GradientDrawable().apply {
                setColor(0xFF2B2B30.toInt())
                cornerRadius = px(28).toFloat()
            }
        }

        fun addSlider(label: String, stream: Int) {
            val title = TextView(this).apply {
                text = label
                textSize = 13f
                setTextColor(0xFFCCCCCC.toInt())
                setPadding(px(8), px(12), 0, px(6))
            }
            val icon = getDrawable(R.drawable.ic_volume)!!.mutate()
            val slider = PillSlider(
                this, icon,
                am.getStreamMaxVolume(stream),
                am.getStreamVolume(stream)
            ) { v ->
                try { am.setStreamVolume(stream, v, 0) } catch (e: SecurityException) { }
            }
            card.addView(title)
            card.addView(slider, LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, px(56)
            ))
        }

        addSlider("Media", AudioManager.STREAM_MUSIC)
        addSlider("Ring", AudioManager.STREAM_RING)
        addSlider("Alarm", AudioManager.STREAM_ALARM)

        val outer = FrameLayout(this).apply { setPadding(px(12), px(12), px(12), px(12)) }
        outer.addView(card, FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ))
        setContentView(outer)
    }
}