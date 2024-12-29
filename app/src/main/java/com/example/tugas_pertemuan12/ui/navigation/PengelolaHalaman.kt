package com.example.tugas_pertemuan12.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugas_pertemuan12.ui.view.DestinasiDetail
import com.example.tugas_pertemuan12.ui.view.DestinasiEntry
import com.example.tugas_pertemuan12.ui.view.DestinasiHome
import com.example.tugas_pertemuan12.ui.view.DetailView
import com.example.tugas_pertemuan12.ui.view.EntryMhsScreen
import com.example.tugas_pertemuan12.ui.view.HomeScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(route = DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                }
            )
        }
        composable(route = DestinasiEntry.route){
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            route = "${DestinasiDetail.route}/{nim}",
            arguments = listOf(
                navArgument("nim") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim")
            nim?.let {
                DetailView(
                    navigateBack = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToEdit =  {

                    }
                )
            }
        }
    }
}