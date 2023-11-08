package com.duydv.vn.foodappkotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.duydv.vn.foodappkotlin.fragment.*

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> CartFragment()
            2 -> FeedbackFragment()
            3 -> ContactFragment()
            4 -> AccountFragment()
            else -> HomeFragment()
        }
    }
}