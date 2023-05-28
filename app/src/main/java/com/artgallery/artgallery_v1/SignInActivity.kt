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
import androidx.constraintlayout.widget.ConstraintLayout
import com.artgallery.artgallery_v1.accountHelper.accountHelper
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers.Main

class SignInActivity : AppCompatActivity() {

    private  val accHelper = accountHelper(MainActivity())

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val sign_up_button = findViewById<View>(R.id.sign_in_text_sign_up_act) as TextView

        sign_up_button.setOnClickListener{
            this.startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
            finish()
        }

        val back = findViewById<View>(R.id.sign_in_back) as ConstraintLayout
        back.setOnClickListener{
            this.startActivity(Intent(this@SignInActivity, ProfileFragment::class.java))
            finish()
        }

        val sign_in_send_button = findViewById<View>(R.id.sign_in_button) as Button
        sign_in_send_button.setOnClickListener{
            val email_editText = findViewById<View>(R.id.sign_in_email) as EditText
            val password_editText = findViewById<View>(R.id.sign_in_password) as EditText
            accHelper.signIn(email_editText.text.toString(),password_editText.text.toString())
            finish()
        }
    }
}
