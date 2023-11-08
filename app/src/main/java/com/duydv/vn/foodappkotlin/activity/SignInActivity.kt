package com.duydv.vn.foodappkotlin.activity

import android.os.Bundle
import android.widget.Toast
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.constant.Constant
import com.duydv.vn.foodappkotlin.constant.GlobalFunction
import com.duydv.vn.foodappkotlin.databinding.ActivitySignInBinding
import com.duydv.vn.foodappkotlin.databinding.ActivitySignUpBinding
import com.duydv.vn.foodappkotlin.model.User
import com.duydv.vn.foodappkotlin.pref.DataLocalManager
import com.duydv.vn.foodappkotlin.utils.StringUtils
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : BaseActivity() {
    private lateinit var mActivitySignInBinding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivitySignInBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(mActivitySignInBinding.root)

        initListener()
    }

    private fun initListener(){
        mActivitySignInBinding.btnSignIn.setOnClickListener { onClickSignIn() }

        mActivitySignInBinding.layoutSignUp.setOnClickListener {
            GlobalFunction.startActivity(this,SignUpActivity::class.java) }

        mActivitySignInBinding.txtForgotPassword.setOnClickListener{
            GlobalFunction.startActivity(this,ForgotPasswordActivity::class.java)
        }
    }

    private fun onClickSignIn() {
        val email = mActivitySignInBinding.edtEmailSignIn.text.toString().trim()
        val password = mActivitySignInBinding.edtPasswordSignIn.text.toString().trim()

        if(StringUtils.isEmpty(email)){
            Toast.makeText(this, getString(R.string.msg_email_required), Toast.LENGTH_SHORT).show();
        } else if (StringUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.msg_password_required), Toast.LENGTH_SHORT).show();
        } else if (!StringUtils.isValidEmail(email)) {
            Toast.makeText(this, getString(R.string.msg_email_invalid), Toast.LENGTH_SHORT).show();
        }else if (password.length < 6) {
            Toast.makeText(this, getString(R.string.msg_password_invalid), Toast.LENGTH_SHORT).show();
        }else{
            //Kiem tra dang nhap admin
            if(mActivitySignInBinding.radAdmin.isChecked){
                if(!email.contains(Constant.EMAIL_ADMIN_FORM)){
                    Toast.makeText(this,R.string.msg_email_admin_invalid,Toast.LENGTH_SHORT).show()
                }else{
                    signInUser(email,password)
                }
            }else{
                //Kiem tra dang nhap nguoi dung
                if(email.contains(Constant.EMAIL_ADMIN_FORM)){
                    Toast.makeText(this, getString(R.string.msg_email_user_invalid), Toast.LENGTH_SHORT).show();
                }else {
                    signInUser(email,password);
                }
            }
        }
    }

    private fun signInUser(email : String, password : String){
        val auth = Firebase.auth

        progressDialog?.show()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progressDialog?.dismiss()
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
                    Toast.makeText(baseContext, R.string.msg_sign_in_fails, Toast.LENGTH_SHORT,).show()
                }
            }
    }
}