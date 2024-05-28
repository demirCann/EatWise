package com.demircandemir.relaysample.presentation.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenTopBar(
    aiModel: String,
    onModelSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(text = "Chat with AI")
        },
        actions = {
            Box(
                modifier = Modifier
                    .clickable { expanded = true }
                    .width(140.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = aiModel,
                        modifier = Modifier.padding(end = 8.dp).weight(1f),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Arrow",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Gemini") },
                        onClick = {
                        onModelSelected("Gemini")
                        expanded = false
                    })
                    DropdownMenuItem(
                        text = { Text("Chat-GPT")},
                        onClick = {
                        onModelSelected("Chat-GPT")
                        expanded = false
                    })
                }
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun ChatScreenTopBarPreview() {
//    ChatScreenTopBar()
//}