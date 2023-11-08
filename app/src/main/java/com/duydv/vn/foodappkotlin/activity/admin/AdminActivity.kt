package com.duydv.vn.foodappkotlin.activity.admin

import android.os.Bundle
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.activity.BaseActivity
import com.duydv.vn.foodappkotlin.adapter.admin.AdminViewpagerAdapter
import com.duydv.vn.foodappkotlin.databinding.ActivityAdminBinding

class AdminActivity : BaseActivity() {
    private lateinit var mActivityAdminBinding : ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityAdminBinding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(mActivityAdminBinding.root)

        val adminViewPagerAdapter = AdminViewpagerAdapter(this)
        mActivityAdminBinding.viewPager.adapter = adminViewPagerAdapter

        mActivityAdminBinding.viewPager.isUserInputEnabled = false

        mActivityAdminBinding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_food -> {
                    mActivityAdminBinding.viewPager.currentItem = 0
                    true
                }
                R.id.nav_feedback -> {
                    mActivityAdminBinding.viewPager.currentItem = 1
                    true
                }
                R.id.nav_order -> {
                    mActivityAdminBinding.viewPager.currentItem = 2
                    true
                }
                R.id.nav_account -> {
                    mActivityAdminBinding.viewPager.currentItem = 3
                    true
                }
                else -> false
            }
        }
    }

    fun showToolbar(title : String){
        mActivityAdminBinding.toolbar.txtTitle.text = title
    }
}