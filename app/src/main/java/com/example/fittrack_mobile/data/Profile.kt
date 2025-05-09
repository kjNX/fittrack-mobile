package com.example.fittrack_mobile.data

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fittrack_mobile.R
import com.example.fittrack_mobile.components.FittrackBottomBar
import com.example.fittrack_mobile.model.*
import com.example.fittrack_mobile.ui.theme.FittrackmobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Objetivos", "Plan", "Ajustes")

    // Mock de datos
    val user = UserProfile(
        name = "Ana García",
        email = "ana.garcia@ejemplo.com",
        avatarRes = R.drawable.avatar_default,
        currentWeight = 68,
        goalWeight = 62,
        progressPercent = 75
    )

    val milestones = listOf(
        Milestone("Primer kilo", MilestoneStatus.COMPLETED),
        Milestone("Mitad del camino", MilestoneStatus.COMPLETED),
        Milestone("Meta final", MilestoneStatus.IN_PROGRESS)
    )

    val nutrition = NutritionPlan(2150, 161, 242, 60)
    var preferences by remember { mutableStateOf(Preferences(true, true)) }

    FittrackmobileTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Mi perfil") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                        }
                    }
                )
            },
            bottomBar = {
                FittrackBottomBar(
                    selectedIndex = 4,
                    onItemSelected = { index ->
                        when (index) {
                            0 -> navController.navigate("home")
                            1 -> navController.navigate("food")
                            2 -> navController.navigate("chat")
                            3 -> navController.navigate("activity")
                            4 -> navController.navigate("profile")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                UserInfoSection(user)

                TabRow(
                    selectedTabIndex = selectedTab,
                    modifier = Modifier.fillMaxWidth(),
                    divider = { Divider(color = Color.Transparent) },
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            height = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }

                when (selectedTab) {
                    0 -> GoalsTab(user, milestones)
                    1 -> PlanTab(nutrition, navController)
                    2 -> SettingsTab(preferences, onPreferencesChange = { preferences = it }, navController)
                }
            }
        }
    }
}

@Composable
private fun UserInfoSection(user: UserProfile) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = user.name, style = MaterialTheme.typography.titleLarge)
        Text(
            text = user.email,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
    Divider(modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
private fun GoalsTab(user: UserProfile, milestones: List<Milestone>) {
    Column(modifier = Modifier.padding(16.dp)) {
        ProfileCard(title = "Mi objetivo de peso") {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Peso actual", color = Color.Gray)
                    Text("${user.currentWeight} kg", fontWeight = FontWeight.Bold)
                }
                Column {
                    Text("Peso objetivo", color = Color.Gray)
                    Text("${user.goalWeight} kg", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = user.progressPercent / 100f,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF4CAF50)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Progreso: ${user.progressPercent}%", modifier = Modifier.align(Alignment.End))
        }

        Spacer(modifier = Modifier.height(16.dp))

        ProfileCard(title = "Próximos hitos") {
            milestones.forEachIndexed { index, milestone ->
                MilestoneItem(
                    title = milestone.title,
                    completed = milestone.status == MilestoneStatus.COMPLETED,
                    progress = if (milestone.status == MilestoneStatus.IN_PROGRESS) user.progressPercent else null
                )
                if (index != milestones.lastIndex) Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Composable
private fun PlanTab(nutrition: NutritionPlan, navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        ProfileCard(title = "Plan alimenticio") {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Text("Calorías diarias")
                Text("${nutrition.dailyCalories} kcal", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                NutrientItem("Proteínas", "${(nutrition.proteinGrams * 4 * 100 / nutrition.dailyCalories)}%", "${nutrition.proteinGrams}g")
                NutrientItem("Carbohidratos", "${(nutrition.carbsGrams * 4 * 100 / nutrition.dailyCalories)}%", "${nutrition.carbsGrams}g")
                NutrientItem("Grasas", "${(nutrition.fatGrams * 9 * 100 / nutrition.dailyCalories)}%", "${nutrition.fatGrams}g")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ProfileCard(title = "Recomendaciones") {
            ClickableItem("Comidas recomendadas", "Basado en tus objetivos") {
                navController.navigate("recommendedMeals")
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ClickableItem("Ejercicios sugeridos", "Para maximizar resultados") {
                navController.navigate("suggestedExercises")
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ClickableItem("Actualizar objetivo", "Modificar tu meta actual") {
                navController.navigate("updateGoal")
            }
        }
    }
}

@Composable
private fun SettingsTab(
    preferences: Preferences,
    onPreferencesChange: (Preferences) -> Unit,
    navController: NavController
) {
    Column(modifier = Modifier.padding(16.dp)) {
        ProfileCard(title = "Preferencias") {
            SwitchItem("Unidades métricas (kg, cm)", preferences.useMetric) {
                onPreferencesChange(preferences.copy(useMetric = it))
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            SwitchItem("Notificaciones", preferences.notificationsEnabled) {
                onPreferencesChange(preferences.copy(notificationsEnabled = it))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ProfileCard(title = "Exportar y conectar") {
            ClickableItem("Descargar informe PDF") { /* Acción PDF */ }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ClickableItem("Conectar con Google Fit") {
                navController.navigate("googleFitConnection")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Cerrar sesión",
            color = MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* Acción logout */ }
                .padding(16.dp)
        )
    }
}

@Composable
private fun ProfileCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
private fun MilestoneItem(title: String, completed: Boolean, progress: Int? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (completed) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Completado",
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(20.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .border(2.dp, Color.Gray, CircleShape)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, modifier = Modifier.weight(1f))

        if (!completed && progress != null) {
            Text("En progreso ($progress%)", color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text("Ver", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun NutrientItem(name: String, percentage: String, grams: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(name, fontWeight = FontWeight.Bold)
        Text(percentage)
        Text(grams, color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun ClickableItem(
    text: String,
    subtitle: String? = null,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp)
    ) {
        Text(text, fontWeight = FontWeight.Bold)
        if (subtitle != null) {
            Text(subtitle, color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
private fun SwitchItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(text, modifier = Modifier.weight(1f))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
        )
    }
}
