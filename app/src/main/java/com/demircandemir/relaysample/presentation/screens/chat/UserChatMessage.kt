package com.demircandemir.relaysample.presentation.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserChatMessage(
    modifier: Modifier = Modifier,
    text: String,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Card(
            modifier = modifier
                .padding(vertical = 4.dp)
                .widthIn(max = screenWidth * 0.7f)
                .background(
                    shape = RoundedCornerShape(
                        topStart = 15.dp,
                        topEnd = 15.dp,
                        bottomStart = 15.dp,
                        bottomEnd = 0.dp
                    ),
                    color = MaterialTheme.colorScheme.primary
                ),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Start,
                lineHeight = 20.sp,
                color = Color.White
            )
        }
    }
}