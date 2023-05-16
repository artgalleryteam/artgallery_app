package com.artgallery.artgallery_v1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.artgallery.artgallery_v1.accountHelper.accountHelper

class SignUpActivity : AppCompatActivity() {

    private  val accHelper = accountHelper(MainActivity())


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val back = findViewById<View>(R.id.sign_up_back) as ConstraintLayout
        back.setOnClickListener{
            this.startActivity(Intent(this@SignUpActivity, ProfileFragment::class.java))
            finish()
        }

        val sign_in_button = findViewById<View>(R.id.sign_up_sign_in_act) as TextView
        sign_in_button.setOnClickListener{
            this.startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
            finish()
        }

        val sign_up_send_button = findViewById<View>(R.id.sign_up_send_bttn) as ConstraintLayout
        sign_up_send_button.setOnClickListener {
            val email_editText = findViewById<View>(R.id.sign_up_email) as EditText
            val password_editText = findViewById<View>(R.id.sign_up_password) as EditText
            val confirm_password_editText = findViewById<View>(R.id.sign_up_rep_password) as EditText
            if(password_editText.text.toString()==confirm_password_editText.text.toString()){
                accHelper.signUpWithEmail(email_editText.text.toString(),password_editText.text.toString())
            }
        }
    }
}