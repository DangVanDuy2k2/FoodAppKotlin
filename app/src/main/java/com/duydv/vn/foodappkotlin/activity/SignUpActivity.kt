package com.duydv.vn.foodappkotlin.activity

import android.os.Bundle
import android.widget.Toast
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.constant.Constant
import com.duydv.vn.foodappkotlin.constant.GlobalFunction
import com.duydv.vn.foodappkotlin.databinding.ActivitySignUpBinding
import com.duydv.vn.foodappkotlin.model.User
import com.duydv.vn.foodappkotlin.pref.DataLocalManager
import com.duydv.vn.foodappkotlin.utils.StringUtils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : BaseActivity() {
    private lateinit var mActivitySignUpBinding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivitySignUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(mActivitySignUpBinding.root)

        mActivitySignUpBinding.imgBack.setOnClickListener{ onBackPressed()}
        mActivitySignUpBinding.btnSignUp.setOnClickListener { onClickSignUp() }
    }

    private fun onClickSignUp() {
        val email = mActivitySignUpBinding.edtEmailSignUp.text.toString().trim()
        val password = mActivitySignUpBinding.edtPasswordSignUp.text.toString().trim()
        val confirmPassword = mActivitySignUpBinding.edtConfirmPasswordSignUp.text.toString().trim();

        if(StringUtils.isEmpty(email)){
            Toast.makeText(this, getString(R.string.msg_email_required), Toast.LENGTH_SHORT).show();
        } else if (StringUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.msg_password_required), Toast.LENGTH_SHORT).show();
        }else if (StringUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, getString(R.string.msg_confirm_password_required), Toast.LENGTH_SHORT).show();
        } else if (!StringUtils.isValidEmail(email)) {
            Toast.makeText(this, getString(R.string.msg_email_invalid), Toast.LENGTH_SHORT).show();
        }else if (password.length < 6) {
            Toast.makeText(this, getString(R.string.msg_password_invalid), Toast.LENGTH_SHORT).show();
        }else if (!confirmPassword.equals(password)) {
            Toast.makeText(this, getString(R.string.msg_confirm_password_invalid), Toast.LENGTH_SHORT).show();
        }else{
            //Kiem tra dang nhap admin
            if(mActivitySignUpBinding.radAdmin.isChecked){
                if(!email.contains(Constant.EMAIL_ADMIN_FORM)){
                    Toast.makeText(this,R.string.msg_email_admin_invalid,Toast.LENGTH_SHORT).show()
                }else{
                    signUpUser(email,password)
                }
            }else{
                //Kiem tra dang nhap nguoi dung
                if(email.contains(Constant.EMAIL_ADMIN_FORM)){
                    Toast.makeText(this, getString(R.string.msg_email_user_invalid), Toast.LENGTH_SHORT).show();
                }else {
                    signUpUser(email,password);
                }
            }
        }
    }

    private fun signUpUser(email:String, password:String){
        val auth = Firebase.auth

        progressDialog?.show()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                progressDialog?.dismiss()
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser

                    val objectUser = user?.email?.let { User(it,password) }

                    if(objectUser!!.email.contains(Constant.EMAIL_ADMIN_FORM)){
                        objectUser.isAdmin = true
                    }
                    DataLocalManager.getInstance().setUser(objectUser)

                    GlobalFunction.startMainActivityOrAdminActivity(this)
                    //Dong tat ca cac activity truoc do
                    finishAffinity()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, R.string.msg_sign_up_fails, Toast.LENGTH_SHORT,).show()
                }
            }
    }
}