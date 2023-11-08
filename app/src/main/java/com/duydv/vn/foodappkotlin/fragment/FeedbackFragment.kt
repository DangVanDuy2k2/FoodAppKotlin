package com.duydv.vn.foodappkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.activity.MainActivity
import com.duydv.vn.foodappkotlin.databinding.FragmentFeedbackBinding

class FeedbackFragment : BaseFragment() {
    private var _mFragmentFeedbackBinding : FragmentFeedbackBinding? = null
    private val mFragmentFeedbackBinding get() = _mFragmentFeedbackBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mFragmentFeedbackBinding = FragmentFeedbackBinding.inflate(inflater,container,false)

        return mFragmentFeedbackBinding.root
    }

    override fun showToolBar() {
        (activity as MainActivity).showToolbar(false,getString(R.string.nav_feedback))
    }
}