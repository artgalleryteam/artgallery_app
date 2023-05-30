package com.artgallery.artgallery_v1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.gridlayout.widget.GridLayout
import androidx.lifecycle.ViewModelProvider
import com.artgallery.artgallery_v1.R
import com.artgallery.artgallery_v1.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

//    val textView: TextView = binding.signInText
//    homeViewModel.text.observe(viewLifecycleOwner) {
//      textView.text = it
//    }
    return root
  }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storage = Firebase.storage
        val listRef = storage.reference.child("pics")

        listRef.listAll()
            .addOnSuccessListener { listResult ->
                val gridLayout: GridLayout = view.findViewById(R.id.home_pic_space)
                gridLayout.columnCount = 2

                val margin = (16 * resources.displayMetrics.density).toInt()
                val imageSize = (170 * resources.displayMetrics.density).toInt()
                val cornerRadius = (25 * resources.displayMetrics.density).toInt()
                val columnSpacing = (7 * resources.displayMetrics.density).toInt()

                listResult.items.forEach { item ->
                    item.downloadUrl.addOnSuccessListener { uri ->
                        val imageView = ImageView(context)
                        imageView.id = View.generateViewId()

                        val layoutParams = GridLayout.LayoutParams()
                        layoutParams.width = imageSize
                        layoutParams.height = imageSize
                        layoutParams.setMargins(columnSpacing, margin, columnSpacing, 0)
                        imageView.layoutParams = layoutParams

                        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

                        gridLayout.addView(imageView)

                        Glide.with(this)
                            .load(uri)
                            .transform(CenterCrop(), RoundedCorners(cornerRadius))
                            .into(imageView)
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
            }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}