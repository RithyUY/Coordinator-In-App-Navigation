package com.rithyuy.coodinatordemo.costome

import android.content.Context
import android.util.AttributeSet
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import java.lang.reflect.AccessibleObject.setAccessible
import androidx.viewpager.widget.ViewPager


class PresentationViewPager : ViewPager {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    fun setDurationScroll(millis: Int) {
        try {
            val viewpager = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, OwnScroller(context, millis))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    inner class OwnScroller(context: Context, durationScroll: Int) : Scroller(context, DecelerateInterpolator()) {

        private var durationScrollMillis = 1

        init {
            this.durationScrollMillis = durationScroll
        }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, durationScrollMillis)
        }
    }

    companion object {

        val DEFAULT_SCROLL_DURATION = 250
        val PRESENTATION_MODE_SCROLL_DURATION = 1000
    }
}
