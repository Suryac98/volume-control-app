package com.example.volume_control

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View

class PillSlider(
    context: Context,
    private val icon: Drawable,
    private val maxValue: Int,
    startValue: Int,
    private val onChange: (Int) -> Unit
) : View(context) {

    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF4A4A52.toInt() }
    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF4F8CFF.toInt() }
    private val rect = RectF()
    private val clip = Path()
    private var value = startValue

    override fun onDraw(canvas: Canvas) {
        val w = width.toFloat()
        val h = height.toFloat()
        val r = h / 2f
        rect.set(0f, 0f, w, h)
        canvas.drawRoundRect(rect, r, r, trackPaint)

        canvas.save()
        clip.reset()
        clip.addRoundRect(rect, r, r, Path.Direction.CW)
        canvas.clipPath(clip)
        canvas.drawRect(0f, 0f, w * value / maxValue, h, fillPaint)
        canvas.restore()

        val size = (h * 0.45f).toInt()
        val left = (h * 0.3f).toInt()
        val top = (h / 2f).toInt() - size / 2
        icon.setBounds(left, top, left + size, top + size)
        icon.draw(canvas)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        if (e.action == MotionEvent.ACTION_DOWN) parent.requestDisallowInterceptTouchEvent(true)
        val fraction = (e.x / width).coerceIn(0f, 1f)
        val v = Math.round(fraction * maxValue)
        if (v != value) {
            value = v
            onChange(v)
            invalidate()
        }
        return true
    }
}