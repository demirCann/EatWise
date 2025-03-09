package com.demircandemir.relaysample.presentation.screens.chat

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demircandemir.relaysample.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChatMessage(
    modifier: Modifier = Modifier,
    text: String = "",
    image: Bitmap? = null,
    time: Long = System.currentTimeMillis(),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    isError: Boolean = false
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val isUserMessage = horizontalAlignment == Alignment.End

    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedTime = timeFormatter.format(Date(time))

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 2.dp),
        horizontalAlignment = horizontalAlignment
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = screenWidth * 0.8f)
                .clip(
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = if (isUserMessage) 20.dp else 4.dp,
                        bottomEnd = if (isUserMessage) 4.dp else 20.dp
                    )
                ),
            colors = CardDefaults.cardColors(
                containerColor = if (isError) 
                    MaterialTheme.colorScheme.errorContainer
                else if (isUserMessage)
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                else
                    MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 6.dp
                )
            ) {
                image?.let { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = stringResource(R.string.shared_photo),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (text.isNotBlank()) {
                    if (isError) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 4.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_error),
                                contentDescription = stringResource(R.string.error_icon),
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Error",
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Text(
                        text = text,
                        color = if (isError)
                            MaterialTheme.colorScheme.onErrorContainer
                        else if (isUserMessage)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )

                    Text(
                        text = formattedTime,
                        color = if (isError)
                            MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.7f)
                        else if (isUserMessage)
                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        fontSize = 10.sp,
                        modifier = Modifier
                            .align(Alignment.End),
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserChatMessagePreview() {
    Column {
        ChatMessage(
            text = "Merhaba, nasılsın?",
            horizontalAlignment = Alignment.Start
        )
        ChatMessage(
            text = "İyiyim, teşekkürler!",
            horizontalAlignment = Alignment.End
        )
    }
}
