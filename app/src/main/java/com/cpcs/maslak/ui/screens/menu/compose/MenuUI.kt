package com.cpcs.maslak.ui.screens.menu.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.cpcs.maslak.ui.screens.commons.MainButton
import com.cpcs.maslak.ui.screens.tools.SizeClass
import com.cpcs.maslak.ui.screens.tools.windowConfiguration
import com.cpcs.maslak.ui.theme.CPCSTestTheme
import com.cpcs.maslak.ui.theme.MainBackgroundColor
import com.cpcs.maslak.ui.theme.MainTextColor

/**
 * Composable function that displays a menu UI with options to add or view items.
 *
 * This menu provides two main actions: adding a new item and viewing existing items. The layout adjusts
 * based on the screen size class to provide an optimal user experience on different devices.
 *
 * @param modifier The [Modifier] to be applied to the root composable. Default is [Modifier].
 * @param onAdd A lambda function that handles the action for adding a new item.
 * @param onView A lambda function that handles the action for viewing existing items.
 */
@Composable
fun MenuUI(
    modifier: Modifier = Modifier,
    onAdd: () -> Unit,
    onView: () -> Unit
) {

    val configuration = LocalConfiguration.current
    val sizeClass = windowConfiguration(configuration)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MainBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.standard)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(R.string.menu),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MainTextColor
            )

            Column(
                modifier.fillMaxWidth(
                    if (sizeClass == SizeClass.COMPACT) 1f else 0.5f
                )
            ) {
                MainButton(
                    text = stringResource(R.string.add_item),
                    onClick = onAdd
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.standard)))

                MainButton(
                    text = stringResource(R.string.view_items),
                    onClick = onView
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.standard)))
        }
    }


}


@Preview
@Composable
private fun MenuUIPrev() {
    CPCSTestTheme {
        MenuUI(
            onAdd = {},
            onView = {}
        )
    }
}