package com.example.fittrack_mobile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fittrack_mobile.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AuthViewModel: ViewModel (){
    private  val auth = Firebase.auth

    private val firestore = Firebase.firestore

    fun login(){

    }

    fun signup(name : String, email:String, password: String,onResult: (Boolean,String)-> Unit){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    var userId= it.result?.user?.uid
                    val userModel = UserModel(name,email,userId!!)
                    firestore.collection("users").document(userId)
                        .set(userModel)
                        .addOnCompleteListener{dbTask->
                            if(dbTask.isSuccessful){
                               onResult(true,"")
                        }else{
                            onResult(false,"Something went wrong")
                        }

                        }
                }else{
                    onResult(false, it.exception?.localizedMessage ?: "Error desconocido")
                }
            }
    }


}