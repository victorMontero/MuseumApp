package com.example.museumapp.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.museumapp.ui.ArtDetailScreen
import com.example.museumapp.ui.MuseumScreen
import com.example.museumapp.ui.navigation.MuseumDestinations.ArtDetailScreen
import com.example.museumapp.ui.navigation.MuseumDestinations.MuseumScreen
import com.example.museumapp.ui.viewmodel.MuseumViewModel


@Composable
fun MuseumNavGraph(navController: NavHostController) {
    val viewModel: MuseumViewModel = hiltViewModel()

    NavHost(navController, MuseumScreen.route) {
        composable(MuseumScreen.route) {

            MuseumScreen(viewModel) { artObject ->
                navController.navigate(ArtDetailScreen.createRoute("artObjectId" to artObject.id))
            }
        }
        composable(ArtDetailScreen.route) { backStackEntry ->
            val artObjectId =
                backStackEntry.arguments?.getString(ArtDetailScreen.artObjectIdArg)
            val artObject = remember { viewModel.getArtObjectById(artObjectId) }
            if (artObject != null) {
                ArtDetailScreen(artObject) {
                    navController.popBackStack()
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Art object not found")
                }
            }
        }
    }
}