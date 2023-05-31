package com.artgallery.artgallery_v1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.gridlayout.widget.GridLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CatalogueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatalogueFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalogue, container, false)
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CatalogueFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CatalogueFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}