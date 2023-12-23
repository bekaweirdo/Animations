package com.bekka.animations.transition_animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bekka.animations.R
import com.bekka.animations.ui.theme.AnimationsTheme

class TransitionAnimationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedImageWithTransition()
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedButtonWithTransition()
    }
}

private enum class ImageState {
    Small, Large
}

@Composable
fun AnimatedImageWithTransition() {

    var imageState by remember {
        mutableStateOf(ImageState.Small)
    }

    val transition = updateTransition(targetState = imageState, label = "BoxState Transition")

    val borderColor by transition.animateColor(label = "BoxState Color Transition") {
        when (it) {
            ImageState.Small -> Color.Red
            ImageState.Large -> Color.Yellow
        }
    }

    val size by transition.animateDp(
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            )
        },
        label = "BoxState Size Transition") {
        when (it) {
            ImageState.Small -> 90.dp
            ImageState.Large -> 130.dp
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(size = size)
                .clip(shape = CircleShape)
                .border(color = borderColor, shape = CircleShape, width = 3.dp),
            painter = painterResource(id = R.drawable.dragon),
            contentDescription = "AnimateImage"
        )

        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = {
                imageState =
                    if (imageState == ImageState.Small)
                        ImageState.Large
                    else
                        ImageState.Small
            }
        ) {
            Text(text = "Toggle State")
        }
    }
}

enum class ButtonState {
    Idle, Loading, Completed
}

@Composable
fun AnimatedButtonWithTransition() {
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

