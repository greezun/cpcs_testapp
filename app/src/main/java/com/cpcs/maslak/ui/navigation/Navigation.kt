package com.cpcs.maslak.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cpcs.maslak.ui.screens.itemform.compose.ItemFormScreen
import com.cpcs.maslak.ui.screens.menu.compose.MenuScreen
import com.cpcs.maslak.ui.screens.useritems.compose.UserItemsScreen

/**
 * Composable function that sets up the navigation graph for the application.
 *
 * This function initializes the navigation controller and defines the navigation routes
 * and their corresponding composable screens.
 *
 * @param modifier The [Modifier] to be applied to the root composable. Default is [Modifier].
 */
@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Routes.MENU) {

        composable(Routes.MENU) {
            MenuScreen(
                modifier = modifier,
                navHostController = navHostController
            )
        }

        composable(Routes.LIST) {
            UserItemsScreen(
                modifier = modifier,
                navHostController = navHostController
            )
        }

        composable(Routes.FORM) {
            ItemFormScreen(modifier = modifier, id = null, navHostController = navHostController)
        }

        composable(
            "${Routes.FORM}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType

            }
            )) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            ItemFormScreen(modifier = modifier, id = id, navHostController = navHostController)
        }
    }

}

