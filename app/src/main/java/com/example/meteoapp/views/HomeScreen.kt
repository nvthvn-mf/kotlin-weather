package com.example.meteoapp.views

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.meteoapp.component.WeatherCell
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.meteoapp.component.AddCityDialog
import com.example.meteoapp.entity.CityEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    cities: List<CityEntity>,
    modifier: Modifier = Modifier,
    onCityClick: (String) -> Unit,
    onCityAdd: (String) -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ma Météo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter une ville")
            }
        }

    ) { innerPadding ->
        LazyColumn(modifier = modifier.padding(innerPadding)) {
            items(cities) { city ->
                Box(
                    modifier = Modifier.clickable {
                        onCityClick(city.name)
                    }
                ) {
                    WeatherCell(city)
                }
            }
        }
        if (showDialog) {
            AddCityDialog(
                onDismiss = { showDialog = false },
                onCityAdded = { name ->
                    onCityAdd(name)
                    showDialog = false
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    //HomeScreen(onCityClick = {})
}