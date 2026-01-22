package com.example.meteoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meteoapp.ui.theme.MeteoAppTheme
import com.example.meteoapp.views.CityDetailScreen
import com.example.meteoapp.views.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeteoAppTheme {
                //Création du controleur de navigation
                val navController = rememberNavController()

                NavHost(navController=navController, startDestination = "home"){
                    composable("home") {
                        HomeScreen(
                            onCityClick = {cityName ->
                                navController.navigate("detail/$cityName")
                            }
                        )
                    }
                    composable (
                        route = "detail/{cityName}",
                        arguments = listOf(navArgument("cityName") { type = NavType.StringType })
                        ) { backStackEntry ->
                        var cityName = backStackEntry.arguments?.getString("cityName")?:"Inconnu"

                        CityDetailScreen(
                            cityName = cityName,
                            onBackClick = {
                                navController.popBackStack()  // Action pour revenir en arrière
                            }
                        )
                    }
                }
                }

            }
        }
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeScreen(onCityClick = {})
}