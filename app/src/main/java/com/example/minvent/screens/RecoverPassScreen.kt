package com.example.minvent.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.minvent.components.BarraSuperiorApp
import com.example.minvent.components.BotonPrincipal
import com.example.minvent.components.InputEmail
import com.example.minvent.components.TituloSeccion
import com.example.minvent.data.DataUsers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoverPassScreen(navController: NavController){
    var email by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }
    var successMensaje by remember { mutableStateOf("") }

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
            TituloSeccion(text = "RECUPERAR CONTRASEÑA")

            InputEmail(
                value = email,
                onValueChange = { email = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonPrincipal(
                text = "Enviar petición",
                onClick = {
                    val userEncontrado = DataUsers.dummyData.any {
                        it.email.equals(email, ignoreCase = true)
                    }

                    if (email.isBlank()) {
                        errorMensaje = "Ingresar correo electrónico"
                        successMensaje = ""
                    } else if (!userEncontrado){
                        errorMensaje = "No se encuentra al usuario"
                        successMensaje = ""
                    } else {
                        errorMensaje = ""
                        successMensaje = "Se ha enviado un correo para recuperar contraseña"
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonPrincipal(
                text = "Ir a Inicio de Sesión",
                onClick = { navController.navigate("login") }
            )


            Spacer(modifier = Modifier.height(16.dp))

            BotonPrincipal(
                text = "Ir a Registro",
                onClick = { navController.navigate("register") }
            )


            if (errorMensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = errorMensaje, color = MaterialTheme.colorScheme.error)
            }
            if (successMensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = successMensaje, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}