package com.example.museumapp.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import coil.compose.AsyncImage
import com.example.museumapp.data.model.ArtObject
import com.example.museumapp.ui.viewmodel.MuseumViewModel

@Composable
fun MuseumScreen(
    viewModel: MuseumViewModel,
    onArtObjectClick: (ArtObject) -> Unit
) {
    val uiState by viewModel.uiState().observeAsState(UiState.Loading)

    LaunchedEffect(Unit) {
        viewModel.getArtObjects()
    }

    Column {
        val context = LocalContext.current
        when (uiState) {
            is UiState.Loading -> {
                Text(text = "Loading...")
            }

            is UiState.Success -> {
                val arts = (uiState as UiState.Success).result
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(arts.size) { index ->
                        val art = arts[index]
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {onArtObjectClick(art)},
                            shape = RectangleShape
                        ) {
                            Box(contentAlignment = Alignment.BottomStart) {
                                AsyncImage(
                                    model = art.webImage.url,
                                    contentDescription = null
                                )
                                Text(text = art.title, fontFamily = FontFamily.Serif)
                            }
                        }
                    }
                }
            }

            is UiState.Error -> {
                Text(text = "Error: ${(uiState as UiState.Error).message}")
            }
        }
    }
}
