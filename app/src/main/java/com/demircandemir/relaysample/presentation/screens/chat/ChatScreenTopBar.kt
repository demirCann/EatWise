package com.demircandemir.relaysample.presentation.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.domain.model.AIModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenTopBar(
    currentModel: AIModel, onModelSelected: (AIModel) -> Unit, onBackPressed: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(title = {
        Text(
            text = stringResource(R.string.chat_with_ai),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }, navigationIcon = {
        IconButton(onClick = onBackPressed) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_arrow),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }, actions = {
        Box {
            FilledTonalButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .height(36.dp)
                    .width(140.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = when (currentModel) {
                            AIModel.GEMINI -> painterResource(R.drawable.ic_gemini)
                            AIModel.GPT -> painterResource(R.drawable.ic_chat_gpt)
                        },
                        contentDescription = stringResource(R.string.ai_model_icon),
                        modifier = Modifier
                            .size(18.dp)
                            .padding(end = 4.dp)
                    )
                    Text(
                        text = currentModel.modelName,
                        style = MaterialTheme.typography.labelLarge,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = stringResource(R.string.dropdown_icon),
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(
                        MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp)
                    )
            ) {
                AIModel.entries.forEach { model ->
                    DropdownMenuItem(text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = when (model) {
                                    AIModel.GEMINI -> painterResource(R.drawable.ic_gemini)
                                    AIModel.GPT -> painterResource(R.drawable.ic_chat_gpt)
                                },
                                contentDescription = stringResource(R.string.ai_model_icon),
                                modifier = Modifier.size(20.dp)
                            )
                            Text(model.modelName)
                        }
                    }, onClick = {
                        onModelSelected(model)
                        expanded = false
                    }, colors = MenuDefaults.itemColors(
                        textColor = if (currentModel == model) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                    )
                }
            }
        }
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary,
        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
    )
    )
}