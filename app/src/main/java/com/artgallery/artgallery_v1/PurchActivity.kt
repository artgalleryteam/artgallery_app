package com.artgallery.artgallery_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class PurchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purch)

        val back = findViewById<View>(R.id.purch_back) as ConstraintLayout
        back.setOnClickListener{
            finish()
        }
    }
}