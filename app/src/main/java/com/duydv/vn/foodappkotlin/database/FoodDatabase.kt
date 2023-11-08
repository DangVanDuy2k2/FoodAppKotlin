package com.duydv.vn.foodappkotlin.database

import FoodDAO
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.duydv.vn.foodappkotlin.model.Food

@Database(entities = [Food :: class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "food.db"
        private var instance: FoodDatabase? = null

        fun getInstance(context: Context) : FoodDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(context,FoodDatabase::class.java, DATABASE_NAME).build()
            }
            return instance!!
        }
    }
    abstract fun foodDAO(): FoodDAO
}