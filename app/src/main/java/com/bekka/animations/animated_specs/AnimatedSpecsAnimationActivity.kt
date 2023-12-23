package com.bekka.animations.animated_specs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bekka.animations.R
import com.bekka.animations.data.DataProvider.AndroidPosition
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TweenSpecAnimationWithLinearEasing()
                        TweenSpecAnimationWithFastOutSlowInEasing()
                        SpringSpecAnimation()
                        KeyframesSpecAnimation()
                        InfiniteRepeatableSpecAnimation()
                    }
                }
            }
        }
    }
}
@Composable
fun TweenSpecAnimationWithLinearEasing() {

    var androidIconState by remember { mutableStateOf(AndroidPosition.Start) }

    val offsetAnimation: Dp by animateDpAsState(
        targetValue = if (androidIconState == AndroidPosition.Start) 0.dp else 350.dp,
        animationSpec = tween(durationMillis = 1500, easing = LinearEasing),
        label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )  {
        Text(text = "LinearEasing")
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
fun TweenSpecAnimationWithFastOutSlowInEasing() {

    var androidIconState by remember { mutableStateOf(AndroidPosition.Start) }

    val offsetAnimation: Dp by animateDpAsState(
        targetValue = if (androidIconState == AndroidPosition.Start) 0.dp else 350.dp,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )  {
        Text(text = "FastOutSlowInEasing")
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
fun SpringSpecAnimation() {
    var expanded by remember { mutableStateOf(false) }
    val size by animateDpAsState(
        targetValue = if (expanded) 200.dp else 50.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )

    Button(onClick = { expanded = !expanded }) {
        Text("Bounce (SpringSpec)", color = Color.White)
    }
    Box(
        Modifier
            .width(size)
            .height(50.dp)
            .background(Color.Red))
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
    Box(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color))
}

@Composable
fun InfiniteRepeatableSpecAnimation() {
    val infiniteTransition = rememberInfiniteTransition(
        label = "infiniteTransition"
    )
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