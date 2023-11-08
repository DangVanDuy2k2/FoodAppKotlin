package com.duydv.vn.foodappkotlin.constant

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.duydv.vn.foodappkotlin.activity.MainActivity
import com.duydv.vn.foodappkotlin.activity.admin.AdminActivity
import com.duydv.vn.foodappkotlin.pref.DataLocalManager

object GlobalFunction {
    fun startActivity(mContext : Context, mClass : Class<*>){
        val intent = Intent(mContext,mClass)
        mContext.startActivity(intent)
    }

    fun startActivity(mContext : Context, mClass : Class<*>,bundle: Bundle){
        val intent = Intent(mContext,mClass)
        intent.putExtras(bundle)
        mContext.startActivity(intent)
    }

    fun startMainActivityOrAdminActivity(mContext: Context){
        if(DataLocalManager.getInstance().getUser()!!.isAdmin){
            val intent = Intent(mContext,AdminActivity::class.java)
            mContext.startActivity(intent)
        }else{
            val intent = Intent(mContext,MainActivity::class.java)
            mContext.startActivity(intent)
        }
    }

    fun hideSoftKeyboard(activity: Activity) {
        try {
            val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        }
    }
}