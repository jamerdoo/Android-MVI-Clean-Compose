package com.dlebal.androidtechnicaltest.presentation.feature

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dlebal.androidtechnicaltest.presentation.feature.detail_screen.DetailScreen
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import com.dlebal.androidtechnicaltest.presentation.core.navigation.Routes
import com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi.CharacterViewModel
import com.dlebal.androidtechnicaltest.presentation.feature.characters.ui.CharactersScreen
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Routes.Home.route) {

        composable(Routes.Home.route) {
            val viewModel: CharacterViewModel = hiltViewModel()
            CharactersScreen(
                viewModel,
                onPostClick = { post ->
                    val json = URLEncoder.encode(
                        Gson().toJson(post),
                        StandardCharsets.UTF_8.toString()
                    )
                    navController.navigate(Routes.Detail.route.plus("/$json"))
                }
            )
        }

        composable(
            route = Routes.Detail.route.plus("/{post}"),
            arguments = listOf(navArgument("post") { type = NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("post")
            val decoded = URLDecoder.decode(json, StandardCharsets.UTF_8.toString())
            val post = Gson().fromJson(decoded, CharacterModel::class.java)
            DetailScreen(data = post)
        }
    }
}