package com.artgallery.artgallery_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
    }

    fun main_intent(view: View){
        val main_intention = Intent(this, MainActivity::class.java)
        startActivity(main_intention)
        finish()
    }
}