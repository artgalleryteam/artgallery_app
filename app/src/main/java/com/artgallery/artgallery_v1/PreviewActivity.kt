package com.artgallery.artgallery_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata

class PreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val preview_button: ConstraintLayout = findViewById(R.id.preview_button)
        preview_button.setOnClickListener {
            val storage = Firebase.storage
            val storageRef = storage.reference
            val picRef = storageRef.child("pics/pic1.jpg")

            val newMetadata = storageMetadata {
                setCustomMetadata("name", "Крестьянка")
                setCustomMetadata("year", "1885")
                setCustomMetadata("author", "Винсент Ван Гог")
                setCustomMetadata("description", "Картина Винсента Ван Гога “Крестьянка с саженцами картофеля” была написана в 1885 году. На картине изображена крестьянка в поле, сажающая картофель. Картина выполнена в стиле постимпрессионизма и находится в музее Круллер-Мюллер в Оттерло (Нидерланды).")
                setCustomMetadata("style", "Реализм")
            }

            picRef.updateMetadata(newMetadata).addOnSuccessListener {
                // Handle success
                // For example, display a message to the user indicating that the metadata update was successful
                Toast.makeText(this, "Metadata update successful!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                // Handle failure
                // For example, show an error message to the user
                Toast.makeText(this, "An error occurred while updating metadata.", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}