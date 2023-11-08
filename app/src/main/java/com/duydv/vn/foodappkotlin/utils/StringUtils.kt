package com.duydv.vn.foodappkotlin.utils

import android.util.Patterns

object StringUtils {
    fun isEmpty(value : String?) : Boolean{
        return value.isNullOrEmpty()
    }

    fun isValidEmail(email : String?): Boolean {
        if(email == null) {
            return false
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}