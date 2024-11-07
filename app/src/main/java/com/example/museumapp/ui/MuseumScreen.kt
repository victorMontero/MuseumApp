package com.example.museumapp.ui

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
import androidx.compose.ui.text.font.FontFamily
import coil.compose.AsyncImage
import com.example.museumapp.data.model.dto.ArtObjectDTO
import com.example.museumapp.ui.viewmodel.MuseumViewModel

@Composable
fun MuseumScreen(
    viewModel: MuseumViewModel,
    onArtObjectClick: (ArtObjectDTO) -> Unit
) {
    val artGalleryUiState by viewModel.uiState().observeAsState(ArtGalleryUiState.Loading)

    LaunchedEffect(Unit) {
        viewModel.getArtObjects()
    }

    Column {
        when (artGalleryUiState) {
            is ArtGalleryUiState.Loading -> {
                Text(text = "Loading...")
            }

            is ArtGalleryUiState.Success -> {
                val arts = (artGalleryUiState as ArtGalleryUiState.Success).result
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(arts.size) { index ->
                        val art = arts[index]
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { onArtObjectClick(art) },
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

            is ArtGalleryUiState.Error -> {
                Text(text = "Error: ${(artGalleryUiState as ArtGalleryUiState.Error).message}")
            }
        }
    }
}
