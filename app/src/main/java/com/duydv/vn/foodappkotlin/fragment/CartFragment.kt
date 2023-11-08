package com.duydv.vn.foodappkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.activity.MainActivity
import com.duydv.vn.foodappkotlin.databinding.FragmentCartBinding

class CartFragment : BaseFragment() {
    private var _mFragmentCartBinding : FragmentCartBinding? = null

    private val mFragmentCartBinding get() = _mFragmentCartBinding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mFragmentCartBinding = FragmentCartBinding.inflate(inflater,container,false)
        
        return mFragmentCartBinding.root
    }

    override fun showToolBar() {
        (activity as MainActivity).showToolbar(false,getString(R.string.nav_cart))
    }
    override fun onDestroy() {
        super.onDestroy()
        _mFragmentCartBinding = null
    }
}