package com.bekka.animations.animation_best_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalAccessibilityManager
import androidx.compose.ui.unit.dp
import com.bekka.animations.ui.theme.AnimationsTheme
import kotlinx.coroutines.delay

class AnimationBestPracticesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AccessibleAnimationDemo()
                }
            }
        }
    }
}

@Composable
fun AccessibleAnimationDemo() {
//    val accessibilityManager = LocalAccessibilityManager.current
//    val reduceMotion = accessibilityManager?.isReduceMotionEnabled ?: false
//
//    var sizeState by remember { mutableStateOf(100.dp) }
//    val size by animateDpAsState(
//        targetValue = sizeState,
//        animationSpec = if (reduceMotion) {
//            spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessLow)
//        } else {
//            tween(durationMillis = 1000)
//        }, label = ""
//    )
//
//    LaunchedEffect(Unit) {
//        while (true) {
//            sizeState = if (sizeState == 100.dp) 200.dp else 100.dp
//            delay(1000) // delay between size toggles
//        }
//    }
//
//    Box(
//        modifier = Modifier
//            .size(size)
//            .background(Color.Magenta)
//            .fillMaxSize()
//    )
}

