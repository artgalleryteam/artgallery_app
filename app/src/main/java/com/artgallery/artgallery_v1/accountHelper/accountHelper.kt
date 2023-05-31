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
                    act.profileReload()
//                    act.myAuth.signOut()
                }
                else{
//                    Toast.makeText(act,act.resources.getString(R.string.sign_up_error),Toast.LENGTH_SHORT).show()
                    act.toastMessage(R.string.sign_up_error.toString())
                }
            }
        }
        act.profileReload()
    }

    fun signIn(email: String,password: String){
        if (email.isNotEmpty() && password.isNotEmpty()){
            act.myAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task->
                if(task.isSuccessful){
//                    Toast.makeText(act,act.resources.getString(R.string.sign_in_success),Toast.LENGTH_SHORT).show()
                    act.toastMessage(R.string.sign_in_success.toString())
                    act.profileReload()
                }
                else{
//                    Toast.makeText(act,act.resources.getString(R.string.sign_in_success),Toast.LENGTH_SHORT).show()
                    act.toastMessage(R.string.sign_in_error.toString())
                }
            }
        }
    }

    fun logout(){
        act.myAuth.signOut()
    }





}