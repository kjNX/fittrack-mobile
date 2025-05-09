package com.example.fittrack_mobile.model

import androidx.annotation.DrawableRes

data class Contact(
    @DrawableRes val image: Int,
    val name: String,
    val role: String,
    val status: String = "Available"
)
