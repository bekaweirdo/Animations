package com.bekka.animations.advanced_animated

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.bekka.animations.ui.theme.AnimationsTheme
import kotlinx.coroutines.delay

class AdvancedAnimationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AdvancedAnimationsDemo()
                }
            }
        }
    }
}

@Composable
fun AdvancedAnimationsDemo() {
    CustomAnimationSpecDemo()
    ComplexAnimationWithAnimatable()
    SideEffectAnimation()
    InfiniteTransitionAnimation()
}

@Composable
fun CustomAnimationSpecDemo() {
    // Custom AnimationSpec
    val customAnimationSpec = remember {
        keyframes<Float> {
            durationMillis = 1000
            0f at 0 with LinearEasing
            100f at 500 with FastOutSlowInEasing
            50f at 1000
        }
    }

    val animatedValue by animateFloatAsState(
        targetValue = 50f,
        animationSpec = customAnimationSpec, label = ""
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)
            .fillMaxSize()
            .graphicsLayer { translationY = animatedValue }
    )
}

@Composable
fun ComplexAnimationWithAnimatable() {
    val animatable = remember { Animatable(0f, Float.VectorConverter) }
    LaunchedEffect(Unit) {
        animatable.animateTo(
            targetValue = 360f,
            animationSpec = tween(2000, easing = LinearEasing)
        )
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Green)
            .fillMaxSize()
            .graphicsLayer { rotationZ = animatable.value }
    )
}

@Composable
fun SideEffectAnimation() {
    var startAnimation by remember { mutableStateOf(false) }
    val animatable = remember { Animatable(0f, Float.VectorConverter) }

    LaunchedEffect(startAnimation) {
        if (startAnimation) {
            animatable.animateTo(1f, animationSpec = spring())
            delay(1000) // Pause at the end
            startAnimation = false
        } else {
            animatable.snapTo(0f)
        }
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Magenta)
            .fillMaxSize()
            .graphicsLayer { scaleX = animatable.value; scaleY = animatable.value }
    )
}

@Composable
fun InfiniteTransitionAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Yellow,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color)
            .fillMaxSize()
    )
}