package com.example.fittrack_mobile.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FittrackBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(containerColor = Color(0xFFF5F5F7), tonalElevation = 4.dp) {
        val items = listOf("Inicio", "Comida", "Chat", "Actividad", "Perfil")
        val icons = listOf(
            Icons.Default.Home,
            Icons.Default.Fastfood,
            Icons.Default.Chat,
            Icons.Default.FitnessCenter,
            Icons.Default.Person
        )

        items.forEachIndexed { index, label ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = label
                    )
                },
                label = { Text(label) },
                alwaysShowLabel = true
            )
        }
    }
}
