package com.bekka.animations.two_way_convertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bekka.animations.ui.theme.AnimationsTheme

class TwoWayConvertorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedBoxWithRotation()
                }
            }
        }
    }

    data class BoxData(val size: Dp, val color: Color)

    private val boxDataConverter = TwoWayConverter<BoxData, AnimationVector4D>(
        convertToVector = { boxData ->
            AnimationVector4D(
                boxData.size.value,
                boxData.color.red,
                boxData.color.green,
                boxData.color.blue
            )
        },
        convertFromVector = { vector ->
            BoxData(
                size = Dp(vector.v1),
                color = Color(vector.v2, vector.v3, vector.v4, 1f)
            )
        }
    )

    @Composable
    fun AnimatedBoxWithRotation() {
        var boxState by remember { mutableStateOf(BoxData(50.dp, Color.Blue)) }
        var rotateState by remember { mutableFloatStateOf(0f) }
        var textAppearance by remember { mutableStateOf(false) }

        val animatedBoxData by animateValueAsState(
            targetValue = boxState,
            typeConverter = boxDataConverter,
            animationSpec = tween(durationMillis = 2000), label = ""
        )

        val rotation by animateFloatAsState(
            targetValue = rotateState,
            animationSpec = tween(durationMillis = 2000), label = ""
        )

        LaunchedEffect(rotation){
            if(rotation == 225f) textAppearance = true
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(visible = textAppearance) {
                Text(text = "Welcome droids")
                Spacer(Modifier.height(80.dp))
            }

            Box(
                modifier = Modifier
                    .size(animatedBoxData.size)
                    .graphicsLayer {
                        rotationZ = rotation
                    }
                    .background(animatedBoxData.color)
            )

            Spacer(Modifier.height(10.dp))

            Button(
                modifier = Modifier.padding(top = 30.dp),
                onClick = {
                boxState = BoxData(200.dp, Color.Magenta)
                rotateState += 225f
            }) {
                Text("Animate")
            }
        }
    }
}