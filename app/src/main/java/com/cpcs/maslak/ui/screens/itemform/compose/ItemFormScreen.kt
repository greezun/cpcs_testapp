package com.cpcs.maslak.ui.screens.itemform.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cpcs.maslak.ui.screens.itemform.ItemFormViewModel
import com.cpcs.maslak.ui.screens.itemform.contract.ItemFormContract
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * Composable function that displays the item form screen, integrating with the navigation component and handling
 * the lifecycle of the [ItemFormViewModel].
 *
 * This screen initializes the ViewModel with the provided item ID, handles navigation effects, and displays
 * the item form UI for creating or editing an item.
 *
 * @param modifier The [Modifier] to be applied to the root composable. Default is [Modifier].
 * @param navHostController The [NavHostController] used for navigation actions.
 * @param id The unique identifier of the item to edit, or null if creating a new item.
 */
@Composable
fun ItemFormScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    id: Int?
) {
    val viewModel: ItemFormViewModel = koinViewModel(parameters = { parametersOf(id) })
    var isNavigate by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.handleEvent(ItemFormContract.Event.Update)

    }

    LaunchedEffect(Unit) {
        viewModel.effect.onEach {
            when (it) {
                ItemFormContract.NavigationBack -> {
                    if(!isNavigate) {
                        navHostController.popBackStack()
                        isNavigate = true
                    }
                }
            }
        }.collect()

    }

    ItemFormUI(
        modifier = modifier,
        state = viewModel.state.value,
        onEvent = viewModel::handleEvent
    )
}