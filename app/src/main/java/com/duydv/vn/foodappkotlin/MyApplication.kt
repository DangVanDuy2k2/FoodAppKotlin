package com.duydv.vn.foodappkotlin

import android.app.Application
import android.content.Context
import com.duydv.vn.foodappkotlin.pref.DataLocalManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MyApplication : Application() {
    private lateinit var database: FirebaseDatabase

    override fun onCreate() {
        super.onCreate()
        database = Firebase.database
        DataLocalManager.init(applicationContext)
    }

    companion object{
        fun get(context : Context?) : MyApplication? {
            return context?.applicationContext as MyApplication?
        }
    }

    fun getFoodReferences() : DatabaseReference{
        return database.getReference("food")
    }
}