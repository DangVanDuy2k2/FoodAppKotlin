package com.duydv.vn.foodappkotlin.fragment.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.activity.SignInActivity
import com.duydv.vn.foodappkotlin.activity.admin.AdminActivity
import com.duydv.vn.foodappkotlin.constant.GlobalFunction
import com.duydv.vn.foodappkotlin.databinding.FragmentAdminAccountBinding
import com.duydv.vn.foodappkotlin.fragment.BaseFragment
import com.duydv.vn.foodappkotlin.pref.DataLocalManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminAccountFragment : BaseFragment() {
    private var mFragmentAdminAccountBinding : FragmentAdminAccountBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentAdminAccountBinding = FragmentAdminAccountBinding.inflate(inflater,container,false)

        initListener()

        return mFragmentAdminAccountBinding?.root
    }

    override fun showToolBar() {
        activity?.let {
            (it as AdminActivity).showToolbar(getString(R.string.nav_account))
        }
    }

    private fun initListener() {
        mFragmentAdminAccountBinding?.layoutLogout?.setOnClickListener { onClickLogout() }
    }

    private fun onClickLogout() {
        Firebase.auth.signOut()
        DataLocalManager.getInstance().setUser(null)
        activity?.let { GlobalFunction.startActivity(it,SignInActivity::class.java) }
    }

    override fun onDestroy() {
        super.onDestroy()
        mFragmentAdminAccountBinding = null
    }
}