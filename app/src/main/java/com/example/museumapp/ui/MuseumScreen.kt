package com.example.museumapp.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage

@Composable
fun MuseumScreen(viewModel: MuseumViewModel) {
    val arts by viewModel.artObjects.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getArtObjects()
    }

    Column {
        val context = LocalContext.current
        if (arts.isEmpty()) {
            Text(text = "Loading...")
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(arts.size) { index ->
                    val art = arts[index]
                    Card(modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "Clicked on ${art.title}",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },
                        shape = RectangleShape) {
                        AsyncImage(
                            model = art.webImage.url,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
