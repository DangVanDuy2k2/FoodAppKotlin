package com.duydv.vn.foodappkotlin.pref

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(mContext : Context) {
    //ten sharedpreferences
    private val PREFERENCE_NAME = "MyPreferences"
    private val sharedPreferences : SharedPreferences = mContext.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

    fun putStringValue(key:String, value:String){
        val editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun getStringValue(key:String) : String?{
        return sharedPreferences.getString(key,"")
    }
}