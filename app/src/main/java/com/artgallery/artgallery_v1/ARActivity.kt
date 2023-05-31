package com.artgallery.artgallery_v1

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ARActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_ar)
            val imageUri = intent.extras?.getString("imageUri")

            val intent = Intent(Intent.ACTION_MAIN)
            intent.component = ComponentName("com.google.ar.core.examples.kotlin.helloar", "com.google.ar.core.examples.kotlin.helloar.HelloArActivity")
            intent.putExtra("pic", imageUri)
            startActivity(intent)
            finish()
        }
}