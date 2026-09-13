package com.example.minvent.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.minvent.data.DataUsers
import com.example.minvent.utils.playSuccessFeedback
import com.example.minvent.components.BotonPrincipal
import com.example.minvent.components.InputEmail
import com.example.minvent.components.InputPassword
import com.example.minvent.components.TituloApp
import com.example.minvent.components.TituloSeccion

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }
    var bienvenidaDialog by remember { mutableStateOf(false) }
    var intentosFallidos by remember { mutableIntStateOf(0) }
    var mostrarDialogoBloqueo by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TituloApp()

            Spacer(modifier = Modifier.height(16.dp))

            TituloSeccion(text = "INICIAR SESIÓN")

            InputEmail(
                value = email,
                onValueChange = { email = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputPassword(
                value = password,
                onValueChange = { password = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            BotonPrincipal(
                text = "Entrar",
                onClick = {
                    val usuarioEncontrado = DataUsers.dummyData.find {
                        it.email.equals(email, ignoreCase = true)
                    }

                    if (email.isBlank() || password.isBlank()) {
                        errorMensaje = "Ingrese los campos para continuar"
                    } else if (usuarioEncontrado == null) {
                        errorMensaje = "El usuario no existe"
                    } else if (usuarioEncontrado.password != password) {
                        intentosFallidos++

                        if (intentosFallidos >= 3) {
                            errorMensaje = "Acceso bloqueado temporalmente"
                            mostrarDialogoBloqueo = true
                        } else {
                            errorMensaje = "Contraseña incorrecta. Intento $intentosFallidos de 3."
                        }
                    } else {
                        intentosFallidos = 0
                        errorMensaje = ""
                        playSuccessFeedback(context)
                        bienvenidaDialog = true
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonPrincipal(
                text = "Ir a Registro",
                onClick = { navController.navigate("register") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonPrincipal(
                text = "Recupera tu contraseña",
                onClick = { navController.navigate("recover") }
            )

            if (errorMensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMensaje,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }


            if (bienvenidaDialog) {
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text("¡Bienvenido!") },
                    text = { Text("Has iniciado sesión correctamente en MINVENT.") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                bienvenidaDialog = false
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        ) {
                            Text("Continuar")
                        }
                    }
                )
            }
        }
    }
}