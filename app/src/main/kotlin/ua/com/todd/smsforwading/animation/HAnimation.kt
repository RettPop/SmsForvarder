package ua.com.todd.smsforwading.animation

import android.view.animation.Animation
import android.view.animation.Transformation
import android.util.Log
import android.view.View

public class HAnimation(val h : Int, val d : Long, val view : View) : Animation() {

    val prevH = view.getLayoutParams().height

    {
        setDuration(d)
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        super.applyTransformation(interpolatedTime, t)
        val currH = h.toFloat() * interpolatedTime
        view.getLayoutParams().height = prevH + currH.toInt()
        view.requestLayout()
    }
}