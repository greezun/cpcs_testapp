package com.cpcs.maslak.ui.screens.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cpcs.maslak.R
import com.cpcs.maslak.ui.theme.MainButtonTextColor
import com.cpcs.maslak.ui.theme.MainColor

/**
 * A composable function that represents a customizable main button with text, color, and click action.
 *
 * This button takes up the full width of its parent, has a predefined height, and allows customization of its
 * container and content colors. It also supports a click action passed as a lambda function.
 *
 * @param text The text to be displayed on the button.
 * @param containerColor The background color of the button. Defaults to [MainColor].
 * @param contentColor The color of the text on the button. Defaults to [MainButtonTextColor].
 * @param onClick The callback function to be executed when the button is clicked.
 */
@Composable
fun MainButton(
    text: String,
    containerColor: Color = MainColor,
    contentColor: Color = MainButtonTextColor,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.button_height)),
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {

        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }

}