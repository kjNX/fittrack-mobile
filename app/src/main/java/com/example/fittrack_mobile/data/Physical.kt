package com.example.fittrack_mobile.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fittrack_mobile.TabbedLayout
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
fun Tracking() {

}

@Composable
fun Training() {
    val activityList = listOf(
        Activity(
            title = "Carrera 5k",
            description = "Entrenamiento para principiantes",
            time = Duration.ZERO
        ),
        Activity(
            title = "Carrera 10k",
            description = "Entrenamiento para principiantes",
            time = Duration.ZERO
        ),
        Activity(
            title = "Carrera 15k",
            description = "Entrenamiento para principiantes",
            time = Duration.ZERO
        )
    )
    LazyColumn {
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
        { Training() }
    )
)

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    FittrackmobileTheme {
        TabbedLayout(physicalSection)
    }
}