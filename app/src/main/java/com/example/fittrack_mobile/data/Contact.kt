package com.example.fittrack_mobile.data

import androidx.annotation.DrawableRes

data class Contact(
    @DrawableRes val image: Int,
    val name: String,
    val role: String,
    val status: String = "Available"
)
