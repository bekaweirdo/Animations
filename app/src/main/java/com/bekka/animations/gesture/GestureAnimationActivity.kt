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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
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
                    SwipeAwayCard()
                }
            }
        }
    }
}

@Composable
fun SwipeAwayCard() {
    // State for tracking the drag amount
    var offsetX by remember { mutableStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()

    // Define the enter and exit animations using animateEnterExit
    val enterTransition = remember {
        fadeIn(animationSpec = TweenSpec(durationMillis = 300)) +
                slideInHorizontally(animationSpec = SpringSpec(stiffness = Spring.StiffnessLow))
    }
    val exitTransition = remember {
        fadeOut(animationSpec = TweenSpec(durationMillis = 300)) +
                slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = SpringSpec(stiffness = Spring.StiffnessMedium))
    }

    // Boolean to control the visibility of the card
    var visible by remember { mutableStateOf(true) }

    // The card composable
    if (visible) {
        Box(
            modifier = Modifier
                .size(300.dp, 180.dp)
                .graphicsLayer {
                    // Apply rotation based on drag distance
                    rotationZ = (offsetX / 20).coerceIn(-30f, 30f)
                    // Adjust the alpha based on the drag distance
                    alpha = 1f - abs(offsetX / 1000).coerceIn(0f, 1f)
                }
                .animateEnterExit(
                    enter = enterTransition,
                    exit = exitTransition
                )
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        offsetX += dragAmount.x
                        if (abs(offsetX) > 300f) { // Threshold for dismissal
                            visible = false
                        }
                    }
                }
        ) {
            // Card content here...
        }
    }
}

@Composable
fun AnimationsTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}
