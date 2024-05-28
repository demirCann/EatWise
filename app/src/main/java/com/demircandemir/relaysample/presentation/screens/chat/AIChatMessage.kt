package com.demircandemir.relaysample.presentation.screens.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AIChatMessage(
    modifier: Modifier = Modifier,
    messageContent: String? = null,
    fontWeight: FontWeight = FontWeight.SemiBold,
    fontSize: TextUnit = 13.sp,
    fontFamily: FontFamily = FontFamily.Default,
    loading: @Composable (() -> Unit)? = null,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {



    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = horizontalAlignment,
    ) {
        Card(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(0.75f),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 15.dp,
                bottomEnd = 15.dp,
                bottomStart = 15.dp
            ),
            colors = CardDefaults.cardColors()
        ) {
            Column(
                modifier = modifier
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    if (loading != null) {
                        loading()
                    }else{
                        Text(
                            text = buildAnnotatedString {
                                append(messageContent ?: "")
                                /*withStyle(
                                    style = SpanStyle(
                                        color = TextPink
                                    )
                                ) {
                                    append("Emmanuel")
                                }
                                append(", I am ChatChef's AI. How can I help you?")*/
                            },
                            fontWeight = fontWeight,
                            fontSize = fontSize,
                            fontFamily = fontFamily,
                            textAlign = TextAlign.Start,
                            lineHeight = 20.sp,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }

                }
            }
        }
    }
}