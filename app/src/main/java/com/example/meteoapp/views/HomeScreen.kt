package com.example.meteoapp.views
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.meteoapp.component.WeatherCell
import com.example.meteoapp.models.City
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, onCityClick: (String) -> Unit) {

    var cities by remember { mutableStateOf(initialiseData()) }

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
                    val newCity = City(
                        id = cities.size,
                        name = name,
                        icon = "sun",
                        temperature = 0.0,
                        weather = "Inconnu"
                    )
                    cities = cities + newCity

                    showDialog = false
                }
            )
        }
    }
}

fun initialiseData(): List<City> {
    var cities = listOf<City>(
        City(id= 0,name= "Paris", icon= "sun", temperature= 18.5, weather= "Nuageux"),
        City(id= 1,name= "Lyon", icon= "sun", temperature= 22.0, weather= "Ensoleillé"),
        City(id= 2,name= "Marseille", icon= "sun", temperature= 25.3, weather= "Chaud"),
        City(id= 3,name= "Toulouse", icon= "sun", temperature= 20.1, weather= "Couvert"),
        City(id= 4,name= "Nice", icon= "sun", temperature= 24.8, weather= "Ensoleillé"),
        City(id= 5,name= "Nantes", icon= "sun", temperature= 16.4, weather= "Pluvieux"),
        City(id= 6,name= "Strasbourg", icon= "sun", temperature= 17.9, weather= "Variable"),
        City(id= 7,name= "Montpellier", icon= "sun", temperature= 23.7, weather= "Beau temps"),
        City(id= 8,name= "Bordeaux", icon= "sun", temperature= 19.6, weather= "Éclaircies"),
        City(id= 9,name= "Lille", icon= "sun", temperature= 15.2, weather= "Pluie")
    )
    return cities
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    HomeScreen(onCityClick = {})
}