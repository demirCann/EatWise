package com.demircandemir.relaysample.presentation.screens.chat

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.util.Constants
import com.demircandemir.relaysample.util.Constants.CAMERA_PERMISSION_REQUIRED
import com.demircandemir.relaysample.util.Constants.GALLERY_OPEN_ERROR
import com.demircandemir.relaysample.util.Constants.MAX_PHOTOS_WARNING
import com.demircandemir.relaysample.util.Constants.PHOTO_CAPTURED
import com.demircandemir.relaysample.util.Constants.PHOTO_LOAD_ERROR
import com.demircandemir.relaysample.util.Constants.PHOTO_SELECTED

@Composable
fun ModernTextFieldContainer(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onImageCaptured: (Boolean, Bitmap?) -> Unit
) {
    val context = LocalContext.current
    var capturedImages by remember { mutableStateOf<List<Bitmap>>(emptyList()) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                try {
                    if (capturedImages.size >= 4) {
                        Toast.makeText(context, MAX_PHOTOS_WARNING, Toast.LENGTH_SHORT).show()
                        return@let
                    }
                    val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                    capturedImages = capturedImages + bitmap
                    onImageCaptured(true, bitmap)
                    Toast.makeText(context, PHOTO_SELECTED, Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(context, PHOTO_LOAD_ERROR, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.extras?.get("data")?.let { bitmap ->
                if (capturedImages.size >= 4) {
                    Toast.makeText(context, MAX_PHOTOS_WARNING, Toast.LENGTH_SHORT).show()
                    return@let
                }
                bitmap as Bitmap
                capturedImages = capturedImages + bitmap
                onImageCaptured(true, bitmap)
                Toast.makeText(context, PHOTO_CAPTURED, Toast.LENGTH_SHORT).show()
            }
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(intent)
        } else {
            Toast.makeText(
                context,
                CAMERA_PERMISSION_REQUIRED,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (capturedImages.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(capturedImages.size) { index ->
                        CapturedImage(
                            image = capturedImages[index],
                            onDeleteClicked = {
                                capturedImages = capturedImages.toMutableList().apply { 
                                    removeAt(index)
                                }
                                if (capturedImages.isEmpty()) {
                                    onImageCaptured(false, null)
                                }
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(4.dp)
                    .zIndex(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (capturedImages.size >= 4) {
                            Toast.makeText(context, MAX_PHOTOS_WARNING, Toast.LENGTH_SHORT).show()
                            return@IconButton
                        }
                        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        try {
                            galleryLauncher.launch(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, GALLERY_OPEN_ERROR, Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_circle),
                        contentDescription = Constants.ADD_BUTTON,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 48.dp),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = Constants.MESSAGE_HINT,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                AnimatedContent(
                    targetState = value.isEmpty(),
                    transitionSpec = {
                        (fadeIn() + scaleIn()).togetherWith(fadeOut() + scaleOut())
                    },
                    modifier = Modifier.size(48.dp),
                    label = "Button Animation"
                ) { isEmpty ->
                    if (isEmpty) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            IconButton(
                                onClick = {
                                    if (capturedImages.size >= 4) {
                                        Toast.makeText(context, MAX_PHOTOS_WARNING, Toast.LENGTH_SHORT).show()
                                        return@IconButton
                                    }
                                    val permission = android.Manifest.permission.CAMERA
                                    when {
                                        ContextCompat.checkSelfPermission(
                                            context,
                                            permission
                                        ) == PackageManager.PERMISSION_GRANTED -> {
                                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                            cameraLauncher.launch(intent)
                                        }
                                        else -> {
                                            cameraPermissionLauncher.launch(permission)
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_camera),
                                    contentDescription = Constants.CAMERA_BUTTON,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = {
                                    onSendClick()
                                    capturedImages = emptyList()
                                },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Send,
                                    contentDescription = Constants.SEND_BUTTON,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CapturedImage(
    image: Bitmap,
    onDeleteClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Image(
            bitmap = image.asImageBitmap(),
            contentDescription = Constants.CAPTURED_PHOTO,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = onDeleteClicked,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 4.dp, y = (-4).dp)
                .size(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                    shape = CircleShape
                )
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = Constants.DELETE_PHOTO,
                modifier = Modifier.size(10.dp),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }
    }
}