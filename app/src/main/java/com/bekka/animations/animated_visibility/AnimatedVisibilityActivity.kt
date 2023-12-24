package com.bekka.animations.animated_visibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bekka.animations.ui.theme.AnimationsTheme

class AnimatedVisibilityActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var firstText by remember { mutableStateOf(false) }
                    var secondText by remember { mutableStateOf(false) }

                    val density = LocalDensity.current
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AnimatedVisibilityFirstText(
                            isVisible = firstText
                        )

                        Spacer(modifier = Modifier.height(26.dp))

                        AnimatedVisibilitySecondText(
                            isVisible = secondText,
                            density = density
                        )

                        Spacer(modifier = Modifier.height(26.dp))

                        Button(
                            onClick = { firstText = !firstText }) {
                            Text(text = if (firstText) "Hide Text 1" else "Show Text 1")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { secondText = !secondText }) {
                            Text(text = if (secondText) "Hide Text 2" else "Show Text 2")
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun AnimatedVisibilityFirstText(isVisible: Boolean) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Text(
                    text = "Here me out, I'm gonna disappear",
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.error)
                        .padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    @Composable
    fun AnimatedVisibilitySecondText(isVisible: Boolean, density: Density) {
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically {
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                "Happy New Year 🎉🎊",
                Modifier
                    .wrapContentSize(),
                fontSize = 30.sp
            )
        }
    }
}
