package com.example.meteoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meteoapp.models.City
import com.example.meteoapp.ui.theme.MeteoAppTheme
import com.example.meteoapp.views.CityDetailScreen
import com.example.meteoapp.views.HomeScreen

class MainActivity : ComponentActivity() {


    @SuppressLint("MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeteoAppTheme {
                var cities by remember { mutableStateOf(initialiseData()) }

                val navController = rememberNavController()

                NavHost(navController=navController, startDestination = "home"){
                    composable("home") {
                        HomeScreen(
                            cities = cities,
                            onCityClick = {cityName ->
                                navController.navigate("detail/$cityName")
                            }
                        )
                    }
                    composable (
                        route = "detail/{cityName}",
                        ) { backStackEntry ->
                        var cityName = backStackEntry.arguments?.getString("cityName")?:"Inconnu"

                        val foundCity = cities.find { it.name == cityName }
                            ?: City(0, "Erreur", "sun", 0.0, "Ville introuvable")
                        CityDetailScreen(
                            city = foundCity,
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
                }

            }
        }
    }

fun initialiseData(): MutableList<City> {
    var cities = listOf(
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
    return cities as MutableList<City>
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeScreen(initialiseData(),onCityClick = {})
}