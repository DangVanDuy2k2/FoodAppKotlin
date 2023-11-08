package com.duydv.vn.foodappkotlin.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.constant.GlobalFunction
import com.duydv.vn.foodappkotlin.pref.DataLocalManager
import com.duydv.vn.foodappkotlin.utils.StringUtils

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ nextActivity() },2500)
    }

    private fun nextActivity() {
        if(DataLocalManager.getInstance().getUser() != null
            && !StringUtils.isEmpty(DataLocalManager.getInstance().getUser()!!.email)){
            //Da dang nhap
            GlobalFunction.startMainActivityOrAdminActivity(this)
        }else{
            //Chua dang nhap
            GlobalFunction.startActivity(this,SignInActivity::class.java)
        }
        finish()
    }
}