package com.magere.learnenglish.extensions

import android.view.animation.AnimationUtils
import com.magere.learnenglish.R
import com.wajahatkarim3.easyflipview.EasyFlipView

fun EasyFlipView.flipTheCard() {
    val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
    when (this.currentFlipState) {
        EasyFlipView.FlipState.FRONT_SIDE -> {
            this.startAnimation(animation)
        }
        else -> {
            this.startAnimation(animation)
            this.flipTheView()
        }
    }
}