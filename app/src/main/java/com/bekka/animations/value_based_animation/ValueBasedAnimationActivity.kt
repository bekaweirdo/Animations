package com.bekka.animations.value_based_animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bekka.animations.R
import com.bekka.animations.data.DataProvider.AndroidPosition
import com.bekka.animations.ui.theme.AnimationsTheme

class ValueBasedAnimationActivity : ComponentActivity() {

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
                        Spacer(modifier = Modifier.height(50.dp))
                        // Float animation example
                        BoxSizeChangeExample()
                        Spacer(modifier = Modifier.height(16.dp))

                        FloatAnimationExample()
                        Spacer(modifier = Modifier.height(16.dp))

                        // Color animation example
                        ColorAnimationExample()
                        Spacer(modifier = Modifier.height(16.dp))

                        // Dp animation example
                        DpAnimationExample()
                        Spacer(modifier = Modifier.height(16.dp))

                        SizeAnimationExample()
                        Spacer(modifier = Modifier.height(16.dp))

                        // ContentSize example
                        ExpandableContentAnimation()
                    }
                }
            }
        }
    }
}

@Composable
fun BoxSizeChangeExample() {
    var size by remember {
        mutableFloatStateOf(0.5f)
    }

    val animateScale by animateFloatAsState(
        targetValue = size,
        animationSpec = tween(durationMillis = 3000),
        label = "BoxSizeChange"
    )

    Box(
        modifier = Modifier
            .scale(scale = animateScale)
            .size(size = 56.dp)
            .background(color = Color.Magenta)
            .clickable {
                size = if (size == 2f) 0.5f else 2f
            }
    )
}

@Composable
fun FloatAnimationExample() {
    var alpha by remember { mutableFloatStateOf(1f) }
    val animatedAlpha by animateFloatAsState(
        targetValue = alpha,
        label = "AlphaChange"
    )

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
    var color by remember {
        mutableStateOf(Color.Red)
    }

    val animateColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(durationMillis = 3000),
        label = "ColorAnimationExample"
    )

    Box(
        modifier = Modifier
            .size(size = 56.dp)
            .background(color = animateColor)
            .clickable {
                color = if (color == Color.Red) Color.Yellow else Color.Red
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Tap")
    }
}

@Composable
fun DpAnimationExample() {

    var androidIconState by remember { mutableStateOf(AndroidPosition.Start) }

    val offsetAnimation: Dp by animateDpAsState(
        targetValue = if (androidIconState == AndroidPosition.Start) 0.dp else 350.dp,
        label = "DpAnimationExample",
        animationSpec = tween(durationMillis = 1500)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )  {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .height(90.dp)
                .absoluteOffset(x = offsetAnimation)
                .clickable {
                    androidIconState = when (androidIconState) {
                        AndroidPosition.Start -> AndroidPosition.Finish
                        AndroidPosition.Finish -> AndroidPosition.Start
                    }
                }
        )
    }

}

@Composable
fun SizeAnimationExample() {
    var expanded by remember { mutableStateOf(false) }
    val animatedSize by animateDpAsState(
        targetValue = if (expanded) 200.dp else 50.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "SizeAnimation"
    )

    Box(
        modifier = Modifier
            .width(animatedSize)
            .height(50.dp)
            .background(Color.Blue)
            .clickable(onClick = { expanded = !expanded })
    ) {
        Text(text = "Size +/-")
    }
}

@Composable
fun ExpandableContentAnimation() {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { expanded = !expanded }) {
            Text(if (expanded) "Collapse" else "Expand")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.onSurface)
                .padding(16.dp)
                .animateContentSize(animationSpec = tween(600))
        ) {
            Column {
                Text(
                    "Tap the button to\nsee more content!",
                    style = MaterialTheme.typography.bodyLarge
                )
                if (expanded) {
                    Text(
                        "This is the extra content that appears when the box is expanded. " +
                                "You can collapse this content by tapping the button again.",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}