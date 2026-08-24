package com.example.minvent.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.minvent.data.DataItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val horizontalScrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventario Principal") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Cerrar sesión"
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
                .padding(16.dp)
        ) {
            Text(
                text = "Listado de Elementos",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(horizontalScrollState)
            ) {
                Column(
                    modifier = Modifier.width(700.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(vertical = 12.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Cód", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
                        Text("Nombre", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                        Text("Autor", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                        Text("Tipo", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.0f))
                        Text("Comentario", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                        Text("Cant.", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
                    }

                    HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primary)


                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(DataItems.dummyItem) { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp, horizontal = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(item.cod, modifier = Modifier.weight(0.8f))
                                Text(item.nombre, modifier = Modifier.weight(1.5f))
                                Text(item.autor, modifier = Modifier.weight(1.2f))
                                Text(item.tipo, modifier = Modifier.weight(1.0f))
                                Text(item.comentario, modifier = Modifier.weight(1.5f))
                                Text(item.cantidad.toString(), modifier = Modifier.weight(0.8f))
                            }
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        }
                    }
                }
            }
        }
    }
}