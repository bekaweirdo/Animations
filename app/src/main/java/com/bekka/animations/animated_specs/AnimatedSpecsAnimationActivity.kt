package com.bekka.animations.animated_specs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.bekka.animations.ui.theme.AnimationsTheme

class AnimatedSpecsAnimationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimationSpecsDemo()
                }
            }
        }
    }
}

@Composable
fun AnimationSpecsDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TweenSpecAnimation()
        SpringSpecAnimation()
        KeyframesSpecAnimation()
        InfiniteRepeatableSpecAnimation()
    }
}

@Composable
fun TweenSpecAnimation() {
    var visible by remember { mutableStateOf(true) }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    Button(onClick = { visible = !visible }) {
        Text("Fade (TweenSpec)", color = Color.White)
    }
    Spacer(modifier = Modifier.height(16.dp))
    Box(Modifier.fillMaxWidth().height(50.dp).alpha(alpha).background(Color.Blue))
}

@Composable
fun SpringSpecAnimation() {
    var expanded by remember { mutableStateOf(false) }
    val size by animateDpAsState(
        targetValue = if (expanded) 200.dp else 50.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = ""
    )

    Button(onClick = { expanded = !expanded }) {
        Text("Bounce (SpringSpec)", color = Color.White)
    }
    Spacer(modifier = Modifier.height(16.dp))
    Box(Modifier.width(size).height(50.dp).background(Color.Red))
}

@Composable
fun KeyframesSpecAnimation() {
    var startAnimation by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (startAnimation) Color.Green else Color.Magenta,
        animationSpec = keyframes {
            durationMillis = 2000
            Color.Yellow at 500 with LinearEasing
            Color.Cyan at 1000
        }, label = ""
    )

    Button(onClick = { startAnimation = !startAnimation }) {
        Text("Color Change (KeyframesSpec)", color = Color.White)
    }
    Spacer(modifier = Modifier.height(16.dp))
    Box(Modifier.fillMaxWidth().height(50.dp).background(color))
}

@Composable
fun InfiniteRepeatableSpecAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Box(
        Modifier
            .size(50.dp)
            .graphicsLayer { rotationZ = angle }
            .background(Color.Green)
    )
}