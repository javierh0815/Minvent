package com.example.minvent.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import com.example.minvent.data.DataUsers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoverPassScreen(navController: NavController){
    var email by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }
    var successMensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Volver al inicio"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Recuperar contraseña",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
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


            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar petición")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Inicio de Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Registro")
        }

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