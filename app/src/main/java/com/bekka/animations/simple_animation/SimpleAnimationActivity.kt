package com.bekka.animations.simple_animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bekka.animations.ui.theme.AnimationsTheme

class SimpleAnimationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Visibility animation example
                        var isVisible by remember { mutableStateOf(true) }
                        AnimatedVisibilityExample(isVisible) { isVisible = !isVisible }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Float animation example
                        FloatAnimationExample()

                        Spacer(modifier = Modifier.height(16.dp))

                        // Color animation example
                        ColorAnimationExample()

                        Spacer(modifier = Modifier.height(16.dp))

                        // Size animation example
                        SizeAnimationExample()
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedVisibilityExample(isVisible: Boolean, onToggleVisibility: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = "Tap to disappear",
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Tap to toggle",
            modifier = Modifier
                .clickable(onClick = onToggleVisibility)
                .padding(8.dp)
        )
    }
}

@Composable
fun FloatAnimationExample() {
    var alpha by remember { mutableStateOf(1f) }
    val animatedAlpha by animateFloatAsState(targetValue = alpha)

    Text(
        text = "Fade me",
        fontSize = 20.sp,
        modifier = Modifier
            .alpha(animatedAlpha)
            .clickable(onClick = { alpha = if (alpha == 1f) 0f else 1f })
            .background(Color.Gray)
            .padding(16.dp)
    )
}

@Composable
fun ColorAnimationExample() {
    var isRed by remember { mutableStateOf(true) }
    val animatedColor by animateColorAsState(
        targetValue = if (isRed) Color.Red else Color.Green,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(animatedColor)
            .clickable(onClick = { isRed = !isRed })
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Tap to change color")
    }
}

@Composable
fun SizeAnimationExample() {
    var expanded by remember { mutableStateOf(false) }
    val animatedSize by animateDpAsState(
        targetValue = if (expanded) 200.dp else 50.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), label = ""
    )

    Box(
        modifier = Modifier
            .width(animatedSize)
            .height(50.dp)
            .background(Color.Blue)
            .clickable(onClick = { expanded = !expanded })
    ) {
        // Box content here
    }
}

@Composable
fun AnimationsTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}
