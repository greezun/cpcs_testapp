package com.cpcs.maslak.ui.screens.itemform.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.cpcs.maslak.R
import com.cpcs.maslak.ui.screens.commons.MainButton
import com.cpcs.maslak.ui.screens.itemform.contract.ItemFormContract
import com.cpcs.maslak.ui.screens.tools.SizeClass
import com.cpcs.maslak.ui.screens.tools.windowConfiguration
import com.cpcs.maslak.ui.theme.DeleteButtonColor
import com.cpcs.maslak.ui.theme.DeleteButtonColorTextColor
import com.cpcs.maslak.ui.theme.MainBackgroundColor
import com.cpcs.maslak.ui.theme.MainColor
import com.cpcs.maslak.ui.theme.MainTextColor
import com.cpcs.maslak.ui.theme.SecondaryTextColor

/**
 * Composable function that renders the UI for an item form, allowing users to create or edit items.
 *
 * This form includes text fields for the item name and description, as well as buttons for saving, deleting,
 * and navigating back. The layout adjusts based on the screen size class and supports both compact and expanded layouts.
 *
 * @param modifier The [Modifier] to be applied to the root composable. Default is [Modifier].
 * @param state The [ItemFormContract.State] representing the current state of the form, including item details and edit mode.
 * @param onEvent A lambda function that handles events from the UI, using [ItemFormContract.Event].
 */
@Composable
fun ItemFormUI(
    modifier: Modifier = Modifier,
    state: ItemFormContract.State,
    onEvent: (ItemFormContract.Event) -> Unit
) {

    val focusManager = LocalFocusManager.current
    val configuration = LocalConfiguration.current
    val sizeClass = windowConfiguration(configuration)


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MainBackgroundColor),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.standard)),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Box(modifier = Modifier.fillMaxWidth()) {

                IconButton(
                    onClick = { onEvent(ItemFormContract.Event.ComeBack) },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null,
                        tint = MainTextColor
                    )
                }

                Text(
                    text = stringResource(state.screenNameRes),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MainTextColor,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.standard)))

            Column(
                modifier = Modifier
                    .fillMaxWidth(
                        if (sizeClass == SizeClass.COMPACT) 1f else 0.5f
                    )
                    .imePadding()
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = state.itemName,
                    onValueChange = { onEvent(ItemFormContract.Event.SetItemName(it)) },
                    isError = state.itemNameFieldError,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = MainColor,
                        unfocusedBorderColor = MainColor,
                        focusedTextColor = MainTextColor,
                        unfocusedTextColor = MainTextColor,
                        cursorColor = MainTextColor,
                        errorBorderColor = Color.Red,
                        errorTextColor = MainTextColor
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.enter_name),
                            color = SecondaryTextColor,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.standard)))

                OutlinedTextField(
                    value = state.itemDescription,
                    onValueChange = { onEvent(ItemFormContract.Event.SetItemDescription(it)) },
                    minLines = 5,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = MainColor,
                        unfocusedBorderColor = MainColor,
                        focusedTextColor = MainTextColor,
                        unfocusedTextColor = MainTextColor,
                        cursorColor = MainTextColor
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.enter_description),
                            color = SecondaryTextColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.standard)))

                MainButton(text = stringResource(R.string.save)) {
                    onEvent(ItemFormContract.Event.Save)
                }

            }

            if (state.isEditMode) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.standard)))

                MainButton(
                    text = stringResource(R.string.delete),
                    containerColor = DeleteButtonColor,
                    contentColor = DeleteButtonColorTextColor
                ) {
                    onEvent(ItemFormContract.Event.Delete)
                }
            }
        }
    }
}




@Preview
@Composable
private fun ItemFormUIPrev() {
    ItemFormUI(
        state = ItemFormContract.State(
            screenNameRes = R.string.create_new,
            itemName = "",
            itemNameFieldError = false,
            itemDescription = "",
            isEditMode = true
        ),
        onEvent = {}
    )
}