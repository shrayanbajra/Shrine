package com.example.shrine.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Interpolator
import android.widget.ImageView

/**
 * [android.view.View.OnClickListener] used to translate the product grid sheet downward on
 * the Y-axis when the navigation icon in the toolbar is pressed.
 */
class NavigationIconClickListener @JvmOverloads internal constructor(
    private val context: Context,
    private val sheet: View,
    private val interpolator: Interpolator? = null,
    private val openIcon: Drawable? = null,
    private val closeIcon: Drawable? = null
) : View.OnClickListener {

    private val animatorSet = AnimatorSet()
    private var isBackdropShown = false

    init {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    }

    override fun onClick(view: View) {

        isBackdropShown = !isBackdropShown

        cancelAnimation()

        updateIcon(view)

        val translateY = getValueForTranslateY()

        val animator = getAnimator(translateY)

        animatorSet.play(animator)
        animator.start()
    }

    private fun cancelAnimation() {
        // Cancel the existing animations
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()
    }

    private fun updateIcon(view: View) {
        if (openIcon != null && closeIcon != null) {
            if (view !is ImageView) {
                throw IllegalArgumentException("updateIcon() must be called on an ImageView")
            }
            if (isBackdropShown) {
                view.setImageDrawable(closeIcon)
            } else {
                view.setImageDrawable(openIcon)
            }
        }
    }

    private fun getValueForTranslateY(): Double {
        // 80 percent of screen height
        return context.resources.displayMetrics.heightPixels * 0.80
    }

    private fun getAnimator(translateY: Double): ObjectAnimator {
        val animator = ObjectAnimator.ofFloat(
            sheet,
            "translationY",
            (if (isBackdropShown) translateY else 0.0).toFloat()
        )
        animator.duration = 500
        if (interpolator != null) {
            animator.interpolator = interpolator
        }
        return animator
    }
}
