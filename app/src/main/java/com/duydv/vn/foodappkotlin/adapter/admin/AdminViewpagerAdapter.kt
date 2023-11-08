package com.duydv.vn.foodappkotlin.adapter.admin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.duydv.vn.foodappkotlin.fragment.admin.AdminAccountFragment
import com.duydv.vn.foodappkotlin.fragment.admin.AdminFeedbackFragment
import com.duydv.vn.foodappkotlin.fragment.admin.AdminFoodFragment
import com.duydv.vn.foodappkotlin.fragment.admin.AdminOrderFragment

class AdminViewpagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AdminFoodFragment()
            1 -> AdminFeedbackFragment()
            2 -> AdminOrderFragment()
            3 -> AdminAccountFragment()
            else -> AdminFoodFragment()
        }
    }
}