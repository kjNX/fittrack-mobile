package com.example.fittrack_mobile.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fittrack_mobile.ui.theme.FittrackmobileTheme

@Composable
fun FittrackBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp
    ) {
        val items = listOf("Inicio", "Comida", "Chat", "Actividad", "Perfil")
        val icons = listOf(
            Icons.Outlined.Home,
            Icons.Outlined.Fastfood,
            Icons.Outlined.Chat,
            Icons.Outlined.FitnessCenter,
            Icons.Outlined.Person
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
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                )

            )

        }



    }

}
@Preview(showBackground = true)
@Composable
fun FittrackBottomBarPreview() {
    FittrackmobileTheme {
        FittrackBottomBar(selectedIndex = 0, onItemSelected = {})
    }
}


