package com.example.minvent.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.minvent.components.BarraSuperiorApp
import com.example.minvent.components.BotonPrincipal
import com.example.minvent.components.InputEmail
import com.example.minvent.components.InputPassword
import com.example.minvent.components.TituloSeccion
import com.example.minvent.data.DataUsers
import com.example.minvent.data.User
import com.example.minvent.utils.playSuccessFeedback

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPopup by remember { mutableStateOf(false) }
    var errorMensaje by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            BarraSuperiorApp(
                onBackClick = { navController.popBackStack() },
                onHomeClick = {
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TituloSeccion(text = "REGISTRO")

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
                text = "Registrarse",
                onClick = {
                    val userExiste = DataUsers.dummyData.any {
                        it.email.equals(email, ignoreCase = true)
                    }

                    if (userExiste) {
                        errorMensaje = "Usuario ya existe en el sistema"
                        showPopup = false
                    } else {
                        DataUsers.addUser(User(email = email, password = password))

                        errorMensaje = ""
                        playSuccessFeedback(context)
                        showPopup = true
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            BotonPrincipal(
                text = "Ir a Inicio de Sesión",
                onClick = { navController.navigate("login") }
            )


            if (errorMensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = errorMensaje,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (showPopup) {
                AlertDialog(
                    onDismissRequest = { showPopup = false },
                    title = { Text("¡Éxito!") },
                    text = { Text("Registrado correctamente") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showPopup = false
                                navController.navigate("login")
                            }
                        ) {
                            Text("Aceptar")
                        }
                    }
                )
            }
        }
    }
}