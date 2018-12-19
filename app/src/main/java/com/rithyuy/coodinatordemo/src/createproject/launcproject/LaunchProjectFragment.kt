package com.rithyuy.coodinatordemo.src.createproject.launcproject

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.base.BaseFragment
import com.rithyuy.coodinatordemo.base.BaseView
import com.rithyuy.coodinatordemo.di.AppComponent
import com.rithyuy.coodinatordemo.extension.bind
import com.rithyuy.coodinatordemo.extension.bindText
import com.rithyuy.coodinatordemo.extension.startShareElement
import com.rithyuy.coodinatordemo.src.projectdetail.ProjectDetailActivity
import com.rithyuy.coodinatordemo.util.AnimationUtil
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_launch_project.*
import kotlinx.android.synthetic.main.fragment_launch_project.view.*
import java.util.concurrent.TimeUnit

/****
 *
 * Created by UY RITHY on 12/18/18.
 *
 */

class LaunchProjectFragment : BaseFragment<LaunchProjectViewModel>(LaunchProjectViewModel::class.java) {

    override val layoutResource: Int = R.layout.fragment_launch_project
    private val disposables = arrayListOf<Disposable>()

    override fun setupAppComponent(appComponent: AppComponent) {
        appComponent.plus(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnLaunch.setOnClickListener { launch(view.imgRocket) }
        disposables.add(tvProjectName.bind(viewModel.projectNameChange))
    }

    @SuppressLint("CheckResult")
    private fun launch(rocket: ImageView) {
        val slideDown = ObjectAnimator.ofFloat(rocket, View.TRANSLATION_Y, 0f, 200f)
        val scaleX = ObjectAnimator.ofFloat(rocket, View.SCALE_X, 1f, 2f)
        val scaleY = ObjectAnimator.ofFloat(rocket, View.SCALE_Y, 1f, 2f)
        val animator = AnimatorSet()
        animator.play(slideDown).with(scaleX).with(scaleY).with(ObjectAnimator.ofFloat(btnLaunch, View.ALPHA, 1f, 0f))
        animator.duration = 400
        AnimationUtil.animate(animator).subscribe({}, {}, {
            (activity as? AppCompatActivity)?.apply {
                this.startShareElement(ProjectDetailActivity::class.java, Pair(rocket, "rocket"), Pair(containerView, "container"))
                Flowable.just(true)
                        .delay(500, TimeUnit.MILLISECONDS)
                        .observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe { finish() }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.forEach { it.dispose() }
        disposables.clear()
    }
}