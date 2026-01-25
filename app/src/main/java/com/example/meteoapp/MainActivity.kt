package com.example.meteoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.meteoapp.dao.MyAppDataBase
import com.example.meteoapp.entity.CityEntity
import com.example.meteoapp.ui.theme.MeteoAppTheme
import com.example.meteoapp.views.CityDetailScreen
import com.example.meteoapp.views.HomeScreen

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeteoAppTheme {

                //BDD
                val db = Room.databaseBuilder(
                    applicationContext,
                    MyAppDataBase::class.java, "city-database"
                ).allowMainThreadQueries().build()

                val dao = db.cityDao()

                var cities by remember { mutableStateOf(emptyList<CityEntity>()) }

                LaunchedEffect(Unit) {
                    cities = dao.getAllCities()
                }

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            cities = cities,
                            onCityClick = { cityName ->
                                navController.navigate("detail/$cityName")
                            },
                            onCityAdd = { name ->
                                val newCity = CityEntity(
                                    name = name,
                                    icon = "sun",
                                    temperature = 10.0,
                                    weather = "Inconnu"
                                )

                                dao.insert(newCity)
                                cities = dao.getAllCities() // 2. On met à jour l'écran avec la nouvelle liste
                            }
                        )
                    }
                    composable(
                        route = "detail/{cityName}",
                    ) { backStackEntry ->
                        val cityName = backStackEntry.arguments?.getString("cityName") ?: "Inconnu"

                        val foundCity = cities.find { it.name == cityName }
                            ?: CityEntity(0, "Erreur", "sun", 0.0, "Ville introuvable")
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


/*
fun initialiseData(): Unit {
    var cities = listOf(
        CityEntity(name = "Paris", icon = "sun", temperature = 18.5, weather = "Nuageux"),
        CityEntity(name = "Lyon", icon = "sun", temperature = 22.0, weather = "Ensoleillé"),
        CityEntity(name = "Marseille", icon = "sun", temperature = 25.3, weather = "Chaud"),
        CityEntity(name = "Toulouse", icon = "sun", temperature = 20.1, weather = "Couvert"),
        CityEntity(name = "Nice", icon = "sun", temperature = 24.8, weather = "Ensoleillé"),
        CityEntity(name = "Nantes", icon = "sun", temperature = 16.4, weather = "Pluvieux"),
        CityEntity(name = "Strasbourg", icon = "sun", temperature = 17.9, weather = "Variable"),
        CityEntity(name = "Montpellier", icon = "sun", temperature = 23.7, weather = "Beau temps"),
        CityEntity(name = "Bordeaux", icon = "sun", temperature = 19.6, weather = "Éclaircies"),
        CityEntity(name = "Lille", icon = "sun", temperature = 15.2, weather = "Pluie")
    )
    //return cities as MutableList<City>

}

 */

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //HomeScreen(initialiseData(),onCityClick = {})
}