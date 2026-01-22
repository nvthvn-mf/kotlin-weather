package com.example.meteoapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.meteoapp.models.City
import com.example.meteoapp.ui.theme.MeteoAppTheme

@Composable
fun HomeScreen() {
    var cities = initialiseData()

    LazyColumn {
        items(cities) { city -> WeatherCell(city)
        }
    }

    /*
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // Centre le contenu
    ) {
        for(city in cities){

        }

        Text(
            text = "Ma Météo",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    } */
}

fun initialiseData(): List<City> {
    var cities= listOf<City>(
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
        HomeScreen()

}