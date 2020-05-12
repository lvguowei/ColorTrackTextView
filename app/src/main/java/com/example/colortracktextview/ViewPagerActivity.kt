package com.example.colortracktextview

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class ViewPagerActivity : AppCompatActivity() {

    private val items = listOf("Apple", "Pear", "Orange", "Grapes", "Watermelon", "Peach")
    private lateinit var indicatorContainer: LinearLayout
    private val indicators = mutableListOf<ColorTrackTextView>()
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        indicatorContainer = findViewById(R.id.indicatorView)
        viewPager = findViewById(R.id.viewPager)
        initIndicators()
        initViewPager()
    }

    private fun initViewPager() {
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return ItemFragment.newInstance(items[position])
            }

            override fun getCount(): Int {
                return items.size
            }
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val left = indicators.getOrNull(position)
                val right = indicators.getOrNull(position + 1)

                left?.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT)
                left?.setProgress(1 - positionOffset)

                right?.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT)
                right?.setProgress(positionOffset)
            }

            override fun onPageSelected(position: Int) {
            }

        })
    }

    private fun initIndicators() {
        items.forEach { item ->
            val params = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            params.weight = 1f
            ColorTrackTextView(this).apply {
                textSize = 20f
                setOriginColor(Color.BLACK)
                setChangeColor(Color.RED)
                text = item
                layoutParams = params
            }.also {
                indicatorContainer.addView(it)
                indicators.add(it)
            }


        }
    }
}