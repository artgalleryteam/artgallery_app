package com.artgallery.artgallery_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.artgallery.artgallery_v1.accountHelper.accountHelper
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {
    private  val accHelper = accountHelper(MainActivity())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        val back = findViewById<View>(R.id.settings_back) as ConstraintLayout
        back.setOnClickListener{
            finish()
        }

        val logout=findViewById<TextView>(R.id.settings_logout) as TextView
        logout.setOnClickListener{
            accHelper.logout()
            finish()
        }
    }
}