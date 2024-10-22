package com.example.museumapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.museumapp.ui.ArtDetailScreen
import com.example.museumapp.ui.MuseumScreen
import com.example.museumapp.ui.viewmodel.MuseumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MuseumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MuseumAppNavHost(navController, viewModel)
        }
    }

    @Composable
    fun MuseumAppNavHost(navController: NavHostController, viewModel: MuseumViewModel) {
        val context = LocalContext.current
        NavHost(navController = navController, startDestination = "museum") {
            composable("museum") {
                MuseumScreen(viewModel) { artObject ->
                    navController.navigate("artDetail/${artObject.id}")
                }
            }
            composable("artDetail/{artObjectId}") { backStackEntry ->
                val artObjectId = backStackEntry.arguments?.getString("artObjectId")
                val artObject = remember { viewModel.getArtObjectById(artObjectId) }
                if (artObject != null) {
                    ArtDetailScreen(artObject) {
                        navController.popBackStack()
                    }
                }else {
                    Toast.makeText(context, "Art object doesn't has details", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

