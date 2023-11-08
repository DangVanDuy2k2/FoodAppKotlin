package com.duydv.vn.foodappkotlin.activity

import android.os.Bundle
import android.widget.Toast
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.databinding.ActivityForgotPasswordBinding
import com.duydv.vn.foodappkotlin.utils.StringUtils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : BaseActivity() {
    private lateinit var mActivityForgotPasswordBinding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityForgotPasswordBinding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(mActivityForgotPasswordBinding.root)

        mActivityForgotPasswordBinding.imgBack.setOnClickListener { onBackPressed() }
        mActivityForgotPasswordBinding.btnResetPassword.setOnClickListener { onClickResetPassword() }
    }

    private fun onClickResetPassword() {
        val email = mActivityForgotPasswordBinding.edtEmailForgotPassword.text.toString().trim()

        if(StringUtils.isEmpty(email)){
            Toast.makeText(this, getString(R.string.msg_email_required), Toast.LENGTH_SHORT).show()
        }else if (!StringUtils.isValidEmail(email)) {
            Toast.makeText(this, getString(R.string.msg_email_invalid), Toast.LENGTH_SHORT).show()
        }else {
            resetPassword(email)
        }
    }

    private fun resetPassword(email : String){
        progressDialog?.show()
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                progressDialog?.dismiss()
                if (task.isSuccessful) {
                    Toast.makeText(this, getString(R.string.msg_reset_password_successfully), Toast.LENGTH_SHORT).show()
                }
            }
    }
}