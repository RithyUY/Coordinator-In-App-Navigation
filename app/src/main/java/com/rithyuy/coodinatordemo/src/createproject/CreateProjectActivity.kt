package com.rithyuy.coodinatordemo.src.createproject

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.adapter.ViewPagerAdapter
import com.rithyuy.coodinatordemo.src.createproject.launcproject.LaunchProjectFragment
import com.rithyuy.coodinatordemo.src.createproject.projectname.ProjectNameFragment
import com.rithyuy.coodinatordemo.src.createproject.teammember.InviteTeamFragment
import kotlinx.android.synthetic.main.activity_create_project.*
import java.lang.Exception

class CreateProjectActivity : AppCompatActivity(), FragmentHostNavigator {

    private val fragments = arrayListOf<Fragment>(
            ProjectNameFragment().bindHost(this),
            InviteTeamFragment().bindHost(this),
            LaunchProjectFragment()
    )

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_project)
        landingViewPager.adapter = ViewPagerAdapter(supportFragmentManager, fragments)
        landingViewPager.scrollBarFadeDuration = 2
        window.statusBarColor = Color.WHITE
        setupPaging()
    }

    private fun setupPaging() {
        landingViewPager.setDurationScroll(400)
        //landingViewPager.beginFakeDrag()
        landingViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                try {
                    val indicator = indicators.getChildAt(position+1)
                    indicator.visibility = View.VISIBLE
                    indicator.pivotX=0f
                    indicator.scaleX = positionOffset
                    fragments[position].view?.alpha = (1-positionOffset)
                    fragments[position].view?.translationX = - (positionOffsetPixels / 2) + 0f
                    fragments[position + 1].view?.alpha = (positionOffset)
                }catch (ex: Exception){
                    ex.printStackTrace()
                }
            }

            override fun onPageSelected(position: Int) {
            }

        })
    }

    override fun moveNextOf(fragment: Fragment) {
        val page = fragments.indexOf(fragment) +1
        if(page < fragments.size)  landingViewPager.setCurrentItem(page, true)
    }

    override fun movePreviousOf(fragment: Fragment) {
        val page = fragments.indexOf(fragment) -1
        if(page >= 0) {
            landingViewPager.setCurrentItem(page, true)
        }
    }

    override fun onBackPressed() {
        if(landingViewPager.currentItem!=0) {
            movePreviousOf(fragments[landingViewPager.currentItem])
            return
        }
        finish()
    }
}