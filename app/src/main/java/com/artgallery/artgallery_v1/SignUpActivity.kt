package com.artgallery.artgallery_v1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class SignUpActivity : AppCompatActivity() {
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
    }
}