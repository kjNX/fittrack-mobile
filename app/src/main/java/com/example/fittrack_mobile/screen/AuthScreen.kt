package com.example.fittrack_mobile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fittrack_mobile.R
import com.example.fittrack_mobile.ui.theme.AppTypography
import com.example.fittrack_mobile.ui.theme.onBackgroundLight
import com.example.fittrack_mobile.ui.theme.primaryContainerLight
import com.example.fittrack_mobile.ui.theme.onPrimaryLight
import com.example.fittrack_mobile.ui.theme.onTertiaryContainerLight
import com.example.fittrack_mobile.ui.theme.tertiaryLight
import com.example.fittrack_mobile.ui.theme.tertiaryLightMediumContrast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fittrack_mobile.AppUtil
import com.example.fittrack_mobile.viewmodel.AuthViewModel

@Composable
fun AuthScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .background(Color.White)
    ) {
        // Botón de retroceso
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = tertiaryLight
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Títulos
            Text(
                text = "Bienvenido a\nNutriHealth",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                style = AppTypography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Ingresa tu cuenta",
                style = AppTypography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Email Label
            Text(
                text = "Correo electrónico",
                style = AppTypography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("tucorreo@gmail.com") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            // Password Label
            Text(
                text = "Contraseña",
                style = AppTypography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 8.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("ingresa contraseña") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    authViewModel.login(email, password) { success, message ->
                        if (success) {
                            navController.navigate("home")
                        } else {
                            AppUtil.showToast(context, message)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CD964),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Iniciar sesión", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            SocialLoginButton(
                text = "Continuar with Google",
                icon = Icons.Default.Person
            )
            Spacer(modifier = Modifier.height(12.dp))
            SocialLoginButton(
                text = "Continuar con Facebook",
                icon = Icons.Default.AccountCircle
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Pie de página bien centrado y destacado
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("¿No tienes una cuenta?", style = AppTypography.bodyMedium)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Regístrate",
                style = AppTypography.bodyMedium.copy(
                    color = Color(0xFF4CD964), // Verde brillante
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.clickable {
                     navController.navigate("signup")
                }
            )
        }
    }
}

@Composable
fun SocialLoginButton(text: String, icon: ImageVector) {
    OutlinedButton(
        onClick = { /* Acción de login */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(25.dp),
        border = ButtonDefaults.outlinedButtonBorder
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text)
    }
}
