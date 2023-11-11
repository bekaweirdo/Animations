package com.bekka.animations.gesture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.bekka.animations.ui.theme.AnimationsTheme
import kotlin.math.abs

class GestureAnimationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                        LongPressExample()
//                        SwipeGestureExample()
                        PinchAndZoomExample()
                    }
                }
            }
        }
    }
}

@Composable
fun LongPressExample() {
    var isLongPressActive by remember { mutableStateOf(false) }

    Text(
        text = if (isLongPressActive) "Long Pressed" else "Press and Hold",
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, _, _, _ ->
                    isLongPressActive = true
                }
            }
    )
}


@Composable
fun SwipeGestureExample() {
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }

    Text(
        text = "Swipe Me",
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, pan ->
                    // Use pan.x and pan.y here
//                    offset = Offset(offset.x + pan.x, offset.y + pan.y)
                }
            }
    )
}



@Composable
fun PinchAndZoomExample() {
    var scale by remember { mutableStateOf(1f) }

    Text(
        text = "Pinch & Zoom",
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale *= zoom
                    // You can also use pan for panning (offset.x and offset.y)
                }
            }
    )
}