package com.bekka.animations.transition_animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class TransitionAnimationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                // This is the color scheme for your theme. Replace with your actual colors or theme setup.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedVisibilityScreen()
                }
            }
        }
    }
}

@Composable
fun AnimatedVisibilityScreen() {
    var visible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // AnimatedVisibility will automatically animate the visibility changes
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(1000)) + expandIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000)) + shrinkOut(animationSpec = tween(1000))
        ) {
            // This is the content that will appear and disappear
            Text(
                text = "Hello my fellow friends from curiosity",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Button to toggle visibility
        Button(onClick = { visible = !visible }) {
            Text("Toggle Visibility")
        }
    }
}

@Composable
fun AnimationsTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}
