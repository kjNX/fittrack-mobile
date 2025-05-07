package com.example.fittrack_mobile.model

import androidx.compose.runtime.Composable


data class Section(
    val title: String,
    val tabsTitle: List<String>,
    val tabsContent: List<@Composable () -> Unit>
)
