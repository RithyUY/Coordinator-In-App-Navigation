package com.rithyuy.coodinatordemo.util

import android.animation.Animator
import android.animation.ObjectAnimator
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object AnimationUtil {

    fun animate(animator: Animator) : Observable<Boolean> {
        val observable = PublishSubject.create<Boolean>()
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                observable.onComplete()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        animator.start()
        return  observable
    }
}