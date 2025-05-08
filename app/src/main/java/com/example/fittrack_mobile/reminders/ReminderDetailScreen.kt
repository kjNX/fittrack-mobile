package com.example.fittrack_mobile.reminders

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fittrack_mobile.reminders.ReminderTimeUtils
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import org.threeten.bp.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReminderDetailScreen() {
    val context = LocalContext.current
    var isActive by remember { mutableStateOf(true) }
    var reminderTime by remember { mutableStateOf(LocalTime.of(8, 0)) } // 8:00 AM por defecto
    var repeatHours by remember { mutableStateOf(2) }

    val timeUntil = ReminderTimeUtils.minutesUntilReminder(reminderTime).coerceAtLeast(0)
    val hasPassed = ReminderTimeUtils.hasReminderPassed(reminderTime)

    val timeDialogState = rememberMaterialDialogState()

    val history = remember {
        mutableStateListOf(
            "Hoy, 10:30" to true,
            "Hoy, 08:30" to true,
            "Ayer, 18:30" to false,
            "Ayer, 16:30" to true,
            "Ayer, 14:30" to true,
        )
    }

    LaunchedEffect(isActive) {
        try {
            if (isActive) scheduleReminderNotification(context, timeUntil)
        } catch (e: Exception) {
            Log.e("ReminderDetailScreen", "Error scheduling reminder: ${e.localizedMessage}")
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Detalle de recordatorio", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Opacity, contentDescription = null, tint = Color.Blue)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Beber 200ml de agua", fontWeight = FontWeight.Bold)
                        Text("Mantente hidratado durante el día", fontSize = 12.sp, color = Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(onClick = { timeDialogState.show() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Hora programada: ${ReminderTimeUtils.formatTime(reminderTime)}")
                }

                OutlinedTextField(
                    value = repeatHours.toString(),
                    onValueChange = {
                        repeatHours = it.toIntOrNull()?.coerceIn(1, 24) ?: 2
                    },
                    label = { Text("Repetición (horas)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                InfoRow(Icons.Default.AccessTime, "Hora programada", ReminderTimeUtils.formatTime(reminderTime))
                InfoRow(Icons.Default.Repeat, "Repetición", "Cada $repeatHours horas")
                InfoRow(Icons.Default.Notifications, "Próxima notificación", if (hasPassed) "Ya pasó" else "En $timeUntil minutos")

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Recordatorio activo")
                    Switch(checked = isActive, onCheckedChange = {
                        isActive = it
                        try {
                            if (it) scheduleReminderNotification(context, timeUntil)
                            else cancelReminderNotification(context)
                        } catch (e: Exception) {
                            Log.e("ReminderDetailScreen", "Error switching reminder: ${e.localizedMessage}")
                        }
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(
                onClick = {
                    try { scheduleReminderNotification(context, 10) } catch (e: Exception) {
                        Log.e("ReminderDetailScreen", "Error postponing reminder: ${e.localizedMessage}")
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Posponer")
            }
            Button(
                onClick = {
                    try {
                        cancelReminderNotification(context)
                        history.add("Hoy, ${ReminderTimeUtils.formatTime(ReminderTimeUtils.getCurrentTime())}" to true)
                    } catch (e: Exception) {
                        Log.e("ReminderDetailScreen", "Error completing reminder: ${e.localizedMessage}")
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Completar")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Historial", style = MaterialTheme.typography.titleMedium)

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(history) { (hora, completado) ->
                Text(
                    text = "$hora - ${if (completado) "Completado" else "Omitido"}",
                    color = if (completado) Color(0xFF4CAF50) else Color.Red,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }

    MaterialDialog(dialogState = timeDialogState, buttons = { positiveButton("Aceptar") }) {
        timepicker(initialTime = java.time.LocalTime.of(reminderTime.hour, reminderTime.minute)) { selectedTime ->
            reminderTime = LocalTime.of(selectedTime.hour, selectedTime.minute)
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, title: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp), tint = Color.Gray)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(title, fontSize = 12.sp, color = Color.Gray)
            Text(value, fontWeight = FontWeight.Medium)
        }
    }
}

fun scheduleReminderNotification(context: Context, minutesDelay: Long) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, ReminderReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    val triggerTime = SystemClock.elapsedRealtime() + minutesDelay * 60 * 1000
    alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pendingIntent)
}

fun cancelReminderNotification(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, ReminderReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    alarmManager.cancel(pendingIntent)
}
