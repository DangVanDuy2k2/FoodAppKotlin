package com.duydv.vn.foodappkotlin.fragment

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onResume() {
        super.onResume()
        showToolBar()
    }

    abstract fun showToolBar()
}