package com.example.fittrack_mobile.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fittrack_mobile.R
import com.example.fittrack_mobile.TabbedLayout
import com.example.fittrack_mobile.components.FittrackBottomBar
import com.example.fittrack_mobile.model.Activity
import com.example.fittrack_mobile.model.Section
import com.example.fittrack_mobile.ui.theme.FittrackmobileTheme
import kotlin.time.Duration

@Composable
fun ActivityCard(activity: Activity, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = activity.title, style = MaterialTheme.typography.headlineMedium)
                Button(onClick = {}) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Start")
                }
            }
            Text(
                text = activity.description,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Timelapse, contentDescription = "Time")
                Text(text = activity.time.toString(), style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun StatCard(icon: ImageVector, value: String, measure: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Text(text = value, style = MaterialTheme.typography.headlineMedium)
            Text(text = measure, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun Tracking() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.maps),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Column {
                        Text(text = "01:30:20", style = MaterialTheme.typography.headlineLarge)
                        Text(text = "Completa tu objetivo", style = MaterialTheme.typography.labelSmall)
                    }
                    Button(onClick = {}) {
                        Icon(imageVector = Icons.Default.Pause, contentDescription = "Pausar")
                    }
                }
                Row {
                    StatCard(
                        icon = Icons.AutoMirrored.Filled.DirectionsRun,
                        value = "7.0",
                        measure = "km",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(8.dp))
                    StatCard(
                        icon = Icons.Default.LocalFireDepartment,
                        value = "382",
                        measure = "cal",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(8.dp))
                    StatCard(
                        icon = Icons.Default.Speed,
                        value = "12.3",
                        measure = "km/h",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun Training(activityList: List<Activity>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(activityList) { i ->
            ActivityCard(i, modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

val physicalSection = Section(
    title = "Actividad física",
    tabsTitle = listOf("Seguimiento", "Entrenamiento"),
    tabsContent = listOf(
        { Tracking() },
        { Training(activityList) }
    )
)
@Composable
fun ActivityScreen(navController: NavController) {
    FittrackmobileTheme {
        Scaffold(
            bottomBar = {
                FittrackBottomBar(selectedIndex = 3, onItemSelected = { index ->
                    when (index) {
                        0 -> navController.navigate("home")
                        1 -> navController.navigate("food")
                        2 -> navController.navigate("chat")
                        3 -> navController.navigate("activity") // esta misma
                        4 -> navController.navigate("profile")
                    }
                })
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                TabbedLayout(section = physicalSection)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    FittrackmobileTheme {
        TabbedLayout(physicalSection)
    }
}