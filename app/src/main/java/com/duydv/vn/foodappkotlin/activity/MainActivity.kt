package com.duydv.vn.foodappkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.adapter.ViewPagerAdapter
import com.duydv.vn.foodappkotlin.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var mActivityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivityMainBinding.root)

        val mViewPagerAdapter = ViewPagerAdapter(this)
        mActivityMainBinding.viewpager.adapter = mViewPagerAdapter

        mActivityMainBinding.viewpager.isUserInputEnabled = false

        mActivityMainBinding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    mActivityMainBinding.viewpager.currentItem = 0
                    true
                }
                R.id.nav_cart -> {
                    mActivityMainBinding.viewpager.currentItem = 1
                    true
                }
                R.id.nav_feedback -> {
                    mActivityMainBinding.viewpager.currentItem = 2
                    true
                }
                R.id.nav_contact -> {
                    mActivityMainBinding.viewpager.currentItem = 3
                    true
                }
                R.id.nav_account -> {
                    mActivityMainBinding.viewpager.currentItem = 4
                    true
                }
                else -> false
            }
        }
    }

    fun showToolbar(isHome : Boolean,title : String){
        if(isHome){
            mActivityMainBinding.toolbar.layoutToolbar.visibility = View.GONE
        }else{
            mActivityMainBinding.toolbar.layoutToolbar.visibility = View.VISIBLE
            mActivityMainBinding.toolbar.txtTitle.text = title
        }
    }
}