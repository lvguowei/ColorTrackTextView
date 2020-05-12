package com.example.colortracktextview

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.colortracktextview.ColorTrackTextView.Direction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        leftToRightButton.setOnClickListener {
            colorTrackTextView.setDirection(Direction.LEFT_TO_RIGHT)
            ObjectAnimator
                .ofFloat(colorTrackTextView, "progress", 0f, 1f)
                .setDuration(2000)
                .start()
        }

        rightToLeftButton.setOnClickListener {
            colorTrackTextView.setDirection(Direction.RIGHT_TO_LEFT)
            ObjectAnimator
                .ofFloat(colorTrackTextView, "progress", 0f, 1f)
                .setDuration(2000)
                .start()
        }
    }
}
