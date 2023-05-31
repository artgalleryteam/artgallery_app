package com.artgallery.artgallery_v1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        // Retrieve image URI from intent extras
        val imageUri = intent.extras?.getString("imageUri")

        // Load image into ImageView with rounded corners
        val imageView = findViewById<ImageView>(R.id.info_pic)
        val cornerRadius = (25 * resources.displayMetrics.density).toInt()
        Glide.with(this)
            .load(imageUri)
            .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(cornerRadius, 0)))
            .into(imageView)

        val cam = findViewById<LinearLayout>(R.id.info_ar)
        cam.setOnClickListener {
            val intent = Intent(this, ARActivity::class.java)
            val imageUri1 = Uri.parse(imageUri)
            val fileNameWithExtension = imageUri1.getLastPathSegment()
            val fileNameWithoutExtension = fileNameWithExtension?.substringBeforeLast(".")
            intent.putExtra("imageUri", fileNameWithoutExtension)
            startActivity(intent)
        }

        // Get custom metadata and display in TextViews
        val storage = Firebase.storage
        val storageRef = storage.getReferenceFromUrl(imageUri!!)
        storageRef.metadata.addOnSuccessListener { metadata ->
            val name = metadata.getCustomMetadata("name")
            val author = metadata.getCustomMetadata("author")
            val description = metadata.getCustomMetadata("description")

            findViewById<TextView>(R.id.info_name).text = name
            findViewById<TextView>(R.id.info_author).text = author
            findViewById<TextView>(R.id.info_description).text = description
        }

        val back = findViewById<View>(R.id.info_back) as ConstraintLayout
        back.setOnClickListener{
            finish()
        }
    }


}