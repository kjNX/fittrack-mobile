package com.example.fittrack_mobile.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardVoice
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fittrack_mobile.FitCard
import com.example.fittrack_mobile.R
import com.example.fittrack_mobile.model.Message
import com.example.fittrack_mobile.model.Section
import com.example.fittrack_mobile.ui.theme.FittrackmobileTheme

@Composable
fun MessageBubble(message: Message, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(100.dp)
                )
        )
        Spacer(Modifier.width(4.dp))
        Column(
            horizontalAlignment = Alignment.End,
            modifier = modifier
                .background(
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.medium
                )
                .widthIn(max = 190.dp)
                .padding(8.dp)
        ) {
            Text(
                text = message.content,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = message.timestamp,
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun MessageLog(messages: List<Message>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(messages) {message ->
            MessageBubble(message = message)
        }
    }
}

@Composable
fun MessageBar(modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("") }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {}) {
            Icon(imageVector = Icons.Default.KeyboardVoice, contentDescription = "Voice message")
        }
        TextField(value = message, onValueChange = {message = it}, modifier = Modifier
            .weight(1f)
            .padding(horizontal = 8.dp))
        Button(onClick = {}) {
            Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "Send message")
        }
    }
}

@Composable
fun BotMessaging(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        MessageLog(listOf(Message("Hello!")), Modifier.weight(10f))
        MessageBar()
    }
}

@Composable
fun ContactCard(contact: Contact, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(contact.image),
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(100.dp)
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = contact.role, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Composable
fun ResourceCard(title: String, description: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = description, style = MaterialTheme.typography.labelMedium)
            }
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Go")
        }
    }
}

@Composable
fun Contacts(modifier: Modifier = Modifier) {
    Column {
        FitCard(title = "Hablar con un experto", modifier = modifier) {
            ContactCard(Contact(R.drawable.ic_launcher_background, "John Doe", "Expert"))
            Button(onClick = {}) {
                Text(
                    text = "Reservar cita",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        FitCard(title = "Recursos rápidos") {
            ResourceCard(
                title = "Preguntas frecuentes sobre nutrición",
                description = "Respuestas a las dudas más comunes"
            )
        }
    }
}

val supportSection = Section(
    title = "Ayuda y soporte",
    tabsTitle = listOf("Chatbot IA", "Especialista"),
    tabsContent = listOf({ BotMessaging() }, { Contacts() })
)

@Preview(showBackground = true)
@Composable
private fun Preview() {
    FittrackmobileTheme {
        Contacts()
    }
}