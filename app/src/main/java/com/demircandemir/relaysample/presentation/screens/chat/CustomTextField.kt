package com.demircandemir.relaysample.presentation.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.ui.theme.md_theme_light_background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(modifier: Modifier, value: String, updatedText: (String) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = { newText ->
            updatedText(newText)
        },
        modifier = modifier
            .heightIn(54.dp, 108.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(md_theme_light_background)
            .padding(8.dp)
    ) {
        TextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = it,
            singleLine = false,
            enabled = true,
            placeholder = { Text(stringResource(R.string.chat)) },
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(),
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.White
            )
        )
    }
}