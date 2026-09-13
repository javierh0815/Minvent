package com.example.minvent.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.minvent.components.BarraSuperiorHome
import com.example.minvent.components.ItemCard
import com.example.minvent.components.TituloSeccion
import com.example.minvent.data.DataItems

@Composable
fun ItemCardVacio() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay elementos registrados",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {

    val itemsAgrupados by remember {
        derivedStateOf {
            DataItems.dummyItem
                .groupBy { it.cod }
                .map { (_, listaDeCopias) ->
                    Pair(listaDeCopias.first(), listaDeCopias.size)
                }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                BarraSuperiorHome(
                    onLogoutClick = {
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            },
            containerColor = androidx.compose.ui.graphics.Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                TituloSeccion(text = "Listado de Elementos")

                Spacer(modifier = Modifier.height(8.dp))

                if (itemsAgrupados.isEmpty()) {
                    ItemCardVacio()
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(itemsAgrupados) { (item, cantidad) ->
                            ItemCard(item = item, cantidad = cantidad)
                        }
                    }
                }
            }
        }
    }
}