package com.duydv.vn.foodappkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.activity.MainActivity
import com.duydv.vn.foodappkotlin.databinding.FragmentContactBinding

class ContactFragment : BaseFragment() {
    private var _mFragmentContactBinding : FragmentContactBinding? = null
    private val mFragmentContactBinding get() = _mFragmentContactBinding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mFragmentContactBinding = FragmentContactBinding.inflate(inflater,container,false)

        return mFragmentContactBinding.root
    }

    override fun showToolBar() {
        (activity as MainActivity).showToolbar(false,getString(R.string.nav_contact))
    }
}