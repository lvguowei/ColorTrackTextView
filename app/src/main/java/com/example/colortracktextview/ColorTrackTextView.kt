package com.example.colortracktextview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView

class ColorTrackTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var originalPaint: Paint
    private var changePaint: Paint
    private val rect: Rect = Rect()
    private val bounds: Rect = Rect()
    private var progress: Float = 0f

    private var direction: Direction = Direction.LEFT_TO_RIGHT

    enum class Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

    init {
        val array: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView)
        val originalColor =
            array.getColor(R.styleable.ColorTrackTextView_originalColor, textColors.defaultColor)
        val changeColor =
            array.getColor(R.styleable.ColorTrackTextView_changeColor, textColors.defaultColor)
        originalPaint = getPaintByColor(originalColor)
        changePaint = getPaintByColor(changeColor)
        array.recycle()
    }

    fun setDirection(direction: Direction) {
        this.direction = direction
    }

    fun getProgress(): Float {
        return progress
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    private fun getPaintByColor(@ColorInt color: Int): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.color = color
            this.isDither = true
            this.textSize = this@ColorTrackTextView.textSize
        }
    }

    override fun onDraw(canvas: Canvas) {
        val middle = (width * progress).toInt()
        when (direction) {
            Direction.LEFT_TO_RIGHT -> {
                drawText(canvas, changePaint, 0, middle)
                drawText(canvas, originalPaint, middle, width)
            }
            Direction.RIGHT_TO_LEFT -> {
                drawText(canvas, changePaint, width - middle, width)
                drawText(canvas, originalPaint, 0, width - middle)
            }

        }
    }

    private fun drawText(canvas: Canvas, paint: Paint, start: Int, end: Int) {
        canvas.save()

        rect.set(start, 0, end, height)
        canvas.clipRect(rect)

        val textString = text.toString()
        paint.getTextBounds(textString, 0, textString.length, bounds)

        val x = width / 2 - bounds.width() / 2

        val fontMetrics = changePaint.fontMetrics
        val dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
        val baseline = height / 2 + dy

        canvas.drawText(textString, x.toFloat(), baseline, paint)

        canvas.restore()
    }

    fun setChangeColor(color: Int) {
        changePaint.color = color
    }

    fun setOriginColor(color: Int) {
        originalPaint.color = color
    }

}