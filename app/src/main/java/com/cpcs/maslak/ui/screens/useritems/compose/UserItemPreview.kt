package com.cpcs.maslak.ui.screens.useritems.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cpcs.maslak.R
import com.cpcs.maslak.data.model.ItemData
import com.cpcs.maslak.ui.theme.MainColor
import com.cpcs.maslak.ui.theme.MainTextColor
import com.cpcs.maslak.ui.theme.SecondaryTextColor

/**
 * Composable function that displays a preview of a user item with clickable functionality.
 *
 * This preview shows the item's name and description, and is styled with a border. It triggers a callback when clicked,
 * passing the item's ID to the provided click handler.
 *
 * @param item The [ItemData] representing the item to be displayed.
 * @param onClick A lambda function that handles click events, receiving the item's ID as a parameter.
 */
@Composable
fun UserItemPreview(
    item: ItemData,
    onClick: (Int) -> Unit
) {

    Box(
        modifier = Modifier
            .clickable { onClick(item.id) }
            .fillMaxWidth()
            .border(
                border = BorderStroke(width = 1.dp, color = MainColor),
                shape = MaterialTheme.shapes.small
            )
    ) {

        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.standard))) {

            Text(
                text = stringResource(R.string.name),
                color = SecondaryTextColor,

                )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small)))

            Text(
                text = item.name,
                color = MainTextColor,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.standard)))
            Text(
                text = stringResource(R.string.description),
                color = SecondaryTextColor
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small)))

            Text(
                text = item.description,
                color = MainTextColor,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Preview
@Composable
private fun ItemPrev() {
    UserItemPreview(
        item = ItemData(
            id = 0,
            name = "item name",
            description = "item description item descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem descriptionitem description"
        ),
        onClick = {}
    )
}