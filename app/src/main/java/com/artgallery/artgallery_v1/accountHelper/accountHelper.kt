package com.artgallery.artgallery_v1.accountHelper

import android.widget.Toast
import com.artgallery.artgallery_v1.MainActivity
import com.artgallery.artgallery_v1.R
import com.google.firebase.auth.FirebaseUser

class accountHelper(act:MainActivity) {
    private  val act = act
    fun signUpWithEmail(email:String, password:String){
        if (email.isNotEmpty() && password.isNotEmpty()){
            act.myAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task->
                if(task.isSuccessful){
                    sendEmailVerification(task.result?.user!!)
                    act.uiUpdate(task.result?.user)
                }
                else{
                    Toast.makeText(act,act.resources.getString(R.string.sign_up_error),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun sendEmailVerification(user:FirebaseUser){
        user.sendEmailVerification().addOnCompleteListener{task ->
            if (task.isSuccessful){
                Toast.makeText(act,act.resources.getString(R.string.sign_up_ver),Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(act,act.resources.getString(R.string.sign_up_ver_error),Toast.LENGTH_SHORT).show()

            }
        }
    }

}