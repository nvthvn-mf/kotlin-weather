package com.example.meteoapp.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AddCityDialog(
    onDismiss: () -> Unit,
    onCityAdded: (String) -> Unit
) {

    var newCityName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Ajouter une ville") },
        text = {
            TextField(
                value = newCityName,
                onValueChange = { newCityName = it },
                label = { Text("Nom de la ville") }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if (newCityName.isNotEmpty()) {
                        onCityAdded(newCityName)
                    }
                }
            ) {
                Text("Ajouter")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Annuler")
            }
        }
    )
}