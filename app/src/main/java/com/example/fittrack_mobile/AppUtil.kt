package com.example.fittrack_mobile

import android.content.Context
import android.os.Message
import android.widget.Toast

object AppUtil {

    fun showToast(context:Context,message: String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

}