package com.example.meteoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meteoapp.entity.CityEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Cet écran attend le nom de la ville et une fonction pour "revenir en arrière"
fun CityDetailScreen(city: CityEntity, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(city.name) },
                navigationIcon = {
                    // Le bouton retour (<)
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Détails de ${city.name}",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text("${city.temperature?:"--"} °C")
            Text(city.weather?:"Temps Inconnu ")
            Text("Plus d'infos à venir...")
        }
    }
}