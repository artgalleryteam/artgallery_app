package com.artgallery.artgallery_v1.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.gridlayout.widget.GridLayout
import androidx.lifecycle.ViewModelProvider
import com.artgallery.artgallery_v1.InfoActivity
import com.artgallery.artgallery_v1.R
import com.artgallery.artgallery_v1.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private lateinit var imageView: ImageView
    private lateinit var authorTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var yearTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        imageView = view.findViewById(R.id.home_pic)
        authorTextView = view.findViewById(R.id.author_text_view)
        nameTextView = view.findViewById(R.id.name_text_view)
        yearTextView = view.findViewById(R.id.year_text_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val pathReference = storageRef.child("pics")

        pathReference.listAll().addOnSuccessListener { listResult ->
            // Get the list of items in the "pics" folder
            val items = listResult.items

            // Select a random item
            val randomItem = items.random()

            // Get the download URL of the random item
            randomItem.downloadUrl.addOnSuccessListener { uri ->
                // Load the image using Glide
                Glide.with(requireContext())
                    .load(uri)
                    .into(imageView)

                // Add OnClickListener here
                imageView.setOnClickListener {
                    val intent = Intent(activity, InfoActivity::class.java)
                    intent.putExtra("imageUri", uri.toString())
                    startActivity(intent)
                }

                // Get the custom metadata of the image
                randomItem.metadata.addOnSuccessListener { storageMetadata ->
                    val author = storageMetadata.getCustomMetadata("author") ?: "Unknown"
                    val name = storageMetadata.getCustomMetadata("name") ?: "Unknown"
                    val year = storageMetadata.getCustomMetadata("year") ?: "Unknown"

                    // Display the custom metadata in the TextViews
                    authorTextView.text = "$author"
                    nameTextView.text = "$name"
                    yearTextView.text = "$year год"
                }
            }
        }.addOnFailureListener {
            // Display a Toast message
            Toast.makeText(requireContext(), "Error downloading image", Toast.LENGTH_SHORT).show()
        }
    }


override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}