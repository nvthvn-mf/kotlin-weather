package com.example.meteoapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants

@Composable
fun WeatherCell(){
    Card(
        modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 3.dp, vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp) // Marge intérieure (padding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Description de l'image",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit

            )


            Column() {
                Text("Marseille",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold)
                Text("Nuageux")
            }
            Spacer(modifier = Modifier.width(50.dp))

            Column(
                horizontalAlignment= Alignment.End
            ) {
                Text("22",
                    fontSize = 25.sp)
                Text("°C")
            }

        }

    }
}
@Preview
@Composable
fun WeatherCellPreview(){
    WeatherCell()
}