package com.duydv.vn.foodappkotlin.pref

import android.content.Context
import com.duydv.vn.foodappkotlin.model.User
import com.google.gson.Gson

class DataLocalManager {
    private val USER_KEY = "user_key"
    private lateinit var mySharedPreferences: MySharedPreferences

    companion object {
        private var instance: DataLocalManager? = null

        fun init(mContext: Context){
            instance = DataLocalManager()
            instance!!.mySharedPreferences = MySharedPreferences(mContext)
        }

        fun getInstance(): DataLocalManager {
            if(instance == null){
                instance = DataLocalManager()
            }
            return instance!!
        }
    }

    fun setUser(user : User?){
        val gson = Gson()
        val strJsonUser = gson.toJson(user)
        getInstance().mySharedPreferences.putStringValue(USER_KEY,strJsonUser)
    }

    fun getUser() : User?{
        val gson = Gson()
        return gson.fromJson(getInstance().mySharedPreferences.getStringValue(USER_KEY),User::class.java)
    }
}