package com.example.fittrack_mobile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fittrack_mobile.ui.theme.FittrackmobileTheme

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
fun Header(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Title(modifier = Modifier.fillMaxSize())
        FitCard(title = "Calorías de hoy") {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    FittrackmobileTheme {
        Header(modifier = Modifier.padding(24.dp))
    }
}
