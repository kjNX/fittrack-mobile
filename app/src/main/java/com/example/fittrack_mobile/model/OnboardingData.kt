package com.example.fittrack_mobile.model

import androidx.annotation.DrawableRes

data class OnboardingData(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)
