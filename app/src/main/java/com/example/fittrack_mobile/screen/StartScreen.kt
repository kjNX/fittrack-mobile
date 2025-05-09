package com.example.fittrack_mobile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fittrack_mobile.R
import com.example.fittrack_mobile.ui.theme.AppTypography
import com.example.fittrack_mobile.ui.theme.onBackgroundLight
import com.example.fittrack_mobile.ui.theme.onPrimaryLight
import com.example.fittrack_mobile.ui.theme.primaryContainerLight
import com.example.fittrack_mobile.ui.theme.tertiaryLight

@Composable
fun Startcreen(navController: NavHostController,
               modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = primaryContainerLight)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Logo y texto
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_nutrihealth), // reemplaza con tu recurso real
                contentDescription = "NutriHealth Logo",
                modifier = Modifier.size(250.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "NutriHealth",
                style = AppTypography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = onBackgroundLight
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Inicia tu vida saludable",
                style = AppTypography.bodyMedium.copy(
                    color = onBackgroundLight,
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Center
            )
        }

        // Botón Comenzar
        Button(
            onClick = {
                navController.navigate("auth")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = tertiaryLight,
                contentColor = onPrimaryLight
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "Comenzar",
                style = AppTypography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )
        }

    }
}
