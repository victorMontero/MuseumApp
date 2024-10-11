package com.example.museumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.museumapp.ui.MuseumScreen
import com.example.museumapp.ui.MuseumViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MuseumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MuseumScreen(viewModel)
        }
    }
}
