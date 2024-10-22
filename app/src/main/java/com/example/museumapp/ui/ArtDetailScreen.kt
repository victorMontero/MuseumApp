package com.example.museumapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.museumapp.data.model.ArtObject

@Composable
fun ArtDetailScreen(artObject: ArtObject, onBack: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = artObject.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "titulo completo : ${artObject.longTitle}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Description: ${artObject.objectNumber}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Back")
        }
    }
}