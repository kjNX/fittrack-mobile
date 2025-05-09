package com.example.fittrack_mobile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fittrack_mobile.ui.theme.FittrackmobileTheme
import com.example.fittrack_mobile.components.FittrackBottomBar

@Composable
fun HomeScreen(navController: NavController) {
    FittrackmobileTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Scaffold(
                bottomBar = {
                    FittrackBottomBar(selectedIndex = 0, onItemSelected = { /* manejar navegación */ })
                    FittrackBottomBar(selectedIndex = 0, onItemSelected = { index ->
                        when (index) {
                            0 -> navController.navigate("home")
                            1 -> navController.navigate("food")
                            2 -> navController.navigate("chat")
                            3 -> navController.navigate("activity") // esta misma
                            4 -> navController.navigate("profile")
                        }
                    })
                },
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Title(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(16.dp))
                    CaloriesCard()
                    Spacer(modifier = Modifier.height(16.dp))
                    MacronutrientCard()
                    Spacer(modifier = Modifier.height(16.dp))
                    WaterCard()
                    Spacer(modifier = Modifier.height(16.dp))
                    StepsCard()
                    Spacer(modifier = Modifier.height(16.dp))
                    RemindersCard { navController.navigate("reminderDetail") }
                    Spacer(modifier = Modifier.height(16.dp))
                    TrendsCard()
                }
            }
        }
    }
}

@Composable
fun FitCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Column {
            Text(
                text = "¡Hola, Ana!",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Jueves, 25 de abril",
                style = MaterialTheme.typography.labelMedium
            )
        }
        Button(
            onClick = {},
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, Color.LightGray),
            colors = ButtonColors(
                Color.White,
                Color.White,
                Color.White,
                Color.White
            )
        ) {
            Text(text = "Ver informe", color = Color.DarkGray)
        }
    }
}

@Composable
fun RemindersCard(onReminderClick: () -> Unit) {
    FitCard(title = "Recordatorios") {
        Column {
            ReminderItem("Beber 200ml de agua", "En 30 minutos", Icons.Default.Opacity, onClick = onReminderClick)
            ReminderItem("Almuerzo programado", "En 1 hora y 15 minutos", Icons.Default.Restaurant, onClick = onReminderClick)
        }
    }
}

@Composable
fun CaloriesCard() {
    FitCard(title = "Calorías de hoy") {
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text("1450 de 2150 kcal", style = MaterialTheme.typography.bodyLarge)
            LinearProgressIndicator(
                progress = 1450f / 2150f,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun MacronutrientCard() {
    FitCard(title = "Macronutrientes") {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            NutrientProgress("Proteínas", 75f, 161f, MaterialTheme.colorScheme.primary)
            NutrientProgress("Carbos", 120f, 242f, MaterialTheme.colorScheme.tertiary)
            NutrientProgress("Grasas", 40f, 60f, MaterialTheme.colorScheme.secondary)
        }
    }
}

@Composable
fun NutrientProgress(name: String, current: Float, target: Float, color: androidx.compose.ui.graphics.Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(progress = current / target, color = color, strokeWidth = 6.dp)
        Text("${(current / target * 100).toInt()}%", fontSize = 12.sp)
        Text(name, fontSize = 12.sp)
    }
}

@Composable
fun WaterCard() {
    FitCard(title = "Agua") {
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text("1200ml / 2500ml", style = MaterialTheme.typography.bodyLarge)
            LinearProgressIndicator(
                progress = 1200f / 2500f,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun StepsCard() {
    FitCard(title = "Pasos") {
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text("6,500 / 10,000", style = MaterialTheme.typography.bodyLarge)
            LinearProgressIndicator(
                progress = 6500f / 10000f,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ReminderItem(title: String, time: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.padding(end = 8.dp), tint = MaterialTheme.colorScheme.primary)
        Column {
            Text(title, style = MaterialTheme.typography.bodyMedium)
            Text(time, fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
        }
    }
}

@Composable
fun TrendsCard() {
    FitCard(title = "Tendencias") {
        Text("Promedio: 1650 kcal", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
private fun Preview() {
    HomeScreen(navController = NavController(LocalContext.current))
}
