package com.cpcs.maslak.ui.screens.useritems.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cpcs.maslak.ui.navigation.Routes
import com.cpcs.maslak.ui.screens.useritems.UserItemsViewModel
import com.cpcs.maslak.ui.screens.useritems.contract.UserItemsContract
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

/**
 * Composable function that displays the user items screen, managing the navigation and lifecycle of the [UserItemsViewModel].
 *
 * This screen initializes the ViewModel, handles navigation effects, and displays the UI for viewing and interacting with user items.
 *
 * @param modifier The [Modifier] to be applied to the root composable. Default is [Modifier].
 * @param navHostController The [NavHostController] used for handling navigation actions.
 */
@Composable
fun UserItemsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val viewModel: UserItemsViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.handleEvent(UserItemsContract.Event.Update)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.onEach {
            when (it) {
                UserItemsContract.Navigation.NavigationBack -> navHostController.popBackStack()
                is UserItemsContract.Navigation.NavigationToItemForm -> {
                    navHostController.navigate(
                        Routes.FORM + "/${it.value}"
                    )
                }
            }
        }.collect()
    }

    UserItemsUI(
        modifier = modifier,
        state = viewModel.state.value,
        onEvent = viewModel::handleEvent
    )

}