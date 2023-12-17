package com.bekka.animations.transition_animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bekka.animations.ui.theme.AnimationsTheme

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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(1000)) + expandIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000)) + shrinkOut(animationSpec = tween(1000))
        ) {
            Text(
                text = "Hello my fellow friends from curiosity",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { visible = !visible }) {
            Text("Toggle Visibility")
        }

        Spacer(modifier = Modifier.height(20.dp))
        AnimatedButton()
    }
}

enum class ButtonState {
    Idle, Loading, Completed
}

@Composable
fun AnimatedButton() {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }

    val onClick = {
        buttonState = when (buttonState) {
            ButtonState.Idle -> ButtonState.Loading
            ButtonState.Loading -> ButtonState.Completed
            ButtonState.Completed -> ButtonState.Idle
        }
    }

    val transition = updateTransition(targetState = buttonState, label = "ButtonTransition")
    val backgroundColor by transition.animateColor(label = "Background") { state ->
        when (state) {
            ButtonState.Idle -> Color.Gray
            ButtonState.Loading -> Color.Blue
            ButtonState.Completed -> Color.Green
        }
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(when (buttonState) {
            ButtonState.Idle -> "Click Me"
            ButtonState.Loading -> "Loading..."
            ButtonState.Completed -> "Done!"
        })
    }
}

