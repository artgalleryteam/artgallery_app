package com.artgallery.artgallery_v1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.artgallery.artgallery_v1.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
val myAuth = FirebaseAuth.getInstance()
private lateinit var tvAccount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)



    }

    fun sign_in_intent(view: View){
        val sign_in_intention = Intent(this, SignInActivity::class.java)
        startActivity(sign_in_intention)
    }


//    fun uiUpdate(user: FirebaseUser?){
//        tvAccount= findViewById<View>(R.id.profile_name) as TextView
//        tvAccount.text = if(user==null){resources.getString(R.string.non_auth)}else{user.email}
//    }

    fun profileReload(){
        val fragment = supportFragmentManager.findFragmentByTag("profile")
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .remove(fragment)
                .add(R.id.activity_main, ProfileFragment(), "profile")
                .commit()
        }
    }

    fun toastMessage(string: String){
//        val toast=Toast.makeText(this,string,Toast.LENGTH_SHORT)
//        toast.show()
    }
}