package com.duydv.vn.foodappkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.duydv.vn.foodappkotlin.R

abstract class BaseActivity : AppCompatActivity() {
    var builder : AlertDialog.Builder? = null
    var progressDialog : AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createProgressDialog()
    }

    private fun createProgressDialog(){
        builder = AlertDialog.Builder(this)
        builder?.setView(R.layout.dialog)
        progressDialog = builder?.create()
    }
}