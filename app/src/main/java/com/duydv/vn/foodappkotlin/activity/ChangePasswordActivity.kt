package com.duydv.vn.foodappkotlin.activity

import android.os.Bundle
import android.widget.Toast
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.databinding.ActivityChangePasswordBinding
import com.duydv.vn.foodappkotlin.pref.DataLocalManager
import com.duydv.vn.foodappkotlin.utils.StringUtils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePasswordActivity : BaseActivity() {
    private lateinit var mActivityChangePasswordBinding: ActivityChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityChangePasswordBinding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(mActivityChangePasswordBinding.root)

        mActivityChangePasswordBinding.imgBack.setOnClickListener { onBackPressed() }
        mActivityChangePasswordBinding.btnChangePassword.setOnClickListener { onClickChangePassword() }
    }

    private fun onClickChangePassword() {
        val oldPassword = mActivityChangePasswordBinding.edtOldPassword.text.toString().trim();
        val newPassword = mActivityChangePasswordBinding.edtNewPassword.text.toString().trim();
        val confirmPassword = mActivityChangePasswordBinding.edtConfirmNewPassword.text.toString().trim();

        if(StringUtils.isEmpty(oldPassword)){
            Toast.makeText(this, getString(R.string.msg_old_password_required), Toast.LENGTH_SHORT).show()
        } else if (StringUtils.isEmpty(newPassword)) {
            Toast.makeText(this, getString(R.string.msg_new_password_required), Toast.LENGTH_SHORT).show()
        }else if (StringUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, getString(R.string.msg_confirm_new_password_required), Toast.LENGTH_SHORT).show()
        }else if (newPassword.length < 6) {
            Toast.makeText(this, getString(R.string.msg_new_password_invalid), Toast.LENGTH_SHORT).show()
        }else if (confirmPassword != newPassword) {
            Toast.makeText(this, getString(R.string.msg_confirm_new_password_invalid), Toast.LENGTH_SHORT).show()
        }else {
            changePassword(newPassword)
        }
    }

    private fun changePassword(newPassword : String){
        val user = Firebase.auth.currentUser ?: return

        progressDialog?.show()
        user.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                progressDialog?.dismiss()
                if (task.isSuccessful) {
                    Toast.makeText(this,getString(R.string.msg_change_password_successfuly), Toast.LENGTH_SHORT).show();
                    val userLogin = DataLocalManager.getInstance().getUser()
                    userLogin!!.password = newPassword
                    DataLocalManager.getInstance().setUser(userLogin)
                    mActivityChangePasswordBinding.edtOldPassword.setText("")
                    mActivityChangePasswordBinding.edtNewPassword.setText("")
                    mActivityChangePasswordBinding.edtConfirmNewPassword.setText("")
                }
            }
    }
}