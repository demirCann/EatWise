package com.demircandemir.relaysample.presentation.screens.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomContainer(
    modifier: Modifier = Modifier,
    sendButtonClicked: (String) -> Unit = {}
) {
    var value by rememberSaveable { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 85.dp)
            .imePadding()
            .navigationBarsPadding()
    ) {
        CustomTextField(
            modifier = Modifier
                .padding(start = 4.dp)
                .weight(3f),
            value = value
        ) {
            value = it
        }

        IconButton(onClick = {
            sendButtonClicked(value)
            value = ""
        }) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Send Message",
                modifier = Modifier
                    .height(54.dp)
                    .padding(start = 4.dp, end = 4.dp)
                    .weight(0.60f)
            )
        }

    }
}