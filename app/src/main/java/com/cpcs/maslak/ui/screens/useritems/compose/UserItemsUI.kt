package com.cpcs.maslak.ui.screens.useritems.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.cpcs.maslak.R
import com.cpcs.maslak.data.model.ItemData
import com.cpcs.maslak.ui.screens.tools.SizeClass
import com.cpcs.maslak.ui.screens.tools.windowConfiguration
import com.cpcs.maslak.ui.screens.useritems.contract.UserItemsContract
import com.cpcs.maslak.ui.theme.MainBackgroundColor
import com.cpcs.maslak.ui.theme.MainTextColor

/**
 * Composable function that displays the user items UI, allowing users to view and interact with their items.
 *
 * This screen includes a top bar with a back button and title, and a list of items that the user owns.
 * The layout adjusts based on the screen size class to provide an optimal viewing experience.
 *
 * @param modifier The [Modifier] to be applied to the root composable. Default is [Modifier].
 * @param state The [UserItemsContract.State] representing the current state of the UI, including the list of user items.
 * @param onEvent A lambda function that handles events from the UI, using [UserItemsContract.Event].
 */
@Composable
fun UserItemsUI(
    modifier: Modifier = Modifier,
    state: UserItemsContract.State,
    onEvent: (UserItemsContract.Event) -> Unit
) {
    val configuration = LocalConfiguration.current
    val sizeClass = windowConfiguration(configuration)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MainBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.standard)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {

                IconButton(
                    onClick = { onEvent(UserItemsContract.Event.ComeBack) },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null,
                        tint = MainTextColor
                    )
                }

                Text(
                    text = stringResource(R.string.your_items),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MainTextColor,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.standard)))

            Column(
                modifier = Modifier
                    .fillMaxWidth(
                        if (sizeClass == SizeClass.COMPACT) 1f else 0.7f
                    )
                    .weight(1f)
            ) {
                LazyColumn {
                    items(state.itemsList) { item ->
                        UserItemPreview(item = item) {
                            onEvent(UserItemsContract.Event.ViewItem(it))
                        }
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small)))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun UserItemsUIPrev() {
    UserItemsUI(
        state = UserItemsContract.State(
            itemsList = listOf(
                ItemData(
                    id = 0,
                    name = "item name",
                    description = "item description item descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem description"
                ),

                ItemData(
                    id = 0,
                    name = "item name",
                    description = "item description item descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem description"
                ),

                ItemData(
                    id = 0,
                    name = "item name",
                    description = "item description item descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem description"
                )
            )

        ),
        onEvent = {}
    )
}