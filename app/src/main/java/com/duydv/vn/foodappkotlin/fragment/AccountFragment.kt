package com.duydv.vn.foodappkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.activity.ChangePasswordActivity
import com.duydv.vn.foodappkotlin.activity.MainActivity
import com.duydv.vn.foodappkotlin.activity.SignInActivity
import com.duydv.vn.foodappkotlin.constant.GlobalFunction
import com.duydv.vn.foodappkotlin.databinding.FragmentAccountBinding
import com.duydv.vn.foodappkotlin.pref.DataLocalManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountFragment : BaseFragment() {
    private var _mFragmentAccountBinding : FragmentAccountBinding? = null
    private val mFragmentAccountBinding get() = _mFragmentAccountBinding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mFragmentAccountBinding = FragmentAccountBinding.inflate(inflater,container,false)

        showUserInfor();
        mFragmentAccountBinding.layoutLogout.setOnClickListener { onClickLogout() }
        mFragmentAccountBinding.layoutChangePassword.setOnClickListener { activity?.let { it1 ->
            GlobalFunction.startActivity(
                it1,ChangePasswordActivity::class.java)
        } }

        return mFragmentAccountBinding.root
    }

    private fun showUserInfor(){
        val strEmail = DataLocalManager.getInstance().getUser()!!.email
        mFragmentAccountBinding.txtEmail.text = strEmail
    }

    private fun onClickLogout() {
        Firebase.auth.signOut()
        DataLocalManager.getInstance().setUser(null)
        activity?.let { GlobalFunction.startActivity(it,SignInActivity::class.java) }
    }

    override fun showToolBar() {
        (activity as MainActivity).showToolbar(false,getString(R.string.nav_account))
    }
}