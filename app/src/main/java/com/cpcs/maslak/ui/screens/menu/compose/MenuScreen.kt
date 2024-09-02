package com.cpcs.maslak.ui.screens.menu.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cpcs.maslak.ui.navigation.Routes

/**
 * Composable function that displays the menu screen, integrating with the navigation component to
 * handle navigation actions for adding or viewing items.
 *
 * This screen provides two main navigation actions: navigating to the list of items and navigating to the form to add a new item.
 * It ensures that navigation actions are only triggered once to prevent multiple navigations due to rapid user interactions.
 *
 * @param modifier The [Modifier] to be applied to the root composable. Default is [Modifier].
 * @param navHostController The [NavHostController] used for handling navigation actions within the composable.
 */
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    var isNavigate by remember { mutableStateOf(false) }

    MenuUI(
        modifier = modifier,
        onView = {
            if (!isNavigate) {
                navHostController.navigate(Routes.LIST)
                isNavigate = true
            }
        },
        onAdd = {
            if (!isNavigate) {
                navHostController.navigate(Routes.FORM)
                isNavigate = true
            }
        }
    )
}