package com.bekka.animations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bekka.animations.advanced_animated.AdvancedAnimationActivity
import com.bekka.animations.animated_specs.AnimatedSpecsAnimationActivity
import com.bekka.animations.animated_visibility.AnimatedVisibilityActivity
import com.bekka.animations.content_size.ContentSizeAnimationActivity
import com.bekka.animations.data.DataProvider
import com.bekka.animations.data.DataProvider.ADVANCED_ANIMATION
import com.bekka.animations.data.DataProvider.ANIMATED_SPEC_ANIMATION
import com.bekka.animations.data.DataProvider.ANIMATED_VISIBILITY
import com.bekka.animations.data.DataProvider.CONTENT_SIZE_ANIMATION
import com.bekka.animations.data.DataProvider.GESTURE_ANIMATION
import com.bekka.animations.data.DataProvider.SIMPLE_ANIMATION
import com.bekka.animations.data.DataProvider.TRANSITION_ANIMATION
import com.bekka.animations.data.model.AnimationModel
import com.bekka.animations.gesture.GestureAnimationActivity
import com.bekka.animations.simple_animation.SimpleAnimationActivity
import com.bekka.animations.transition_animation.TransitionAnimationActivity
import com.bekka.animations.ui.theme.AnimationsTheme
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(space = 24.dp), // gap between items
                        contentPadding = PaddingValues(all = 22.dp) // padding for LazyColumn layout
                    ) {
                        items(DataProvider.optionsList) { item ->
                            AnimationItem(item)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimationItem(
    optionName: AnimationModel,
    context: Context = LocalContext.current.applicationContext
){
    Card(
        shape = RoundedCornerShape(size = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { context.navigateToAnimation(optionName) }
                .padding(vertical = 10.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(size = 36.dp),
                imageVector = optionName.icon,
                contentDescription = null,
                tint = Color(0xFF6650a4)
            )
            Spacer(modifier = Modifier.width(width = 12.dp))
            Text(
                text = optionName.option,
                fontSize = 16.sp
            )
        }
    }
}

private fun Context.navigateToAnimation(it: AnimationModel){
    when(it.option){
        ANIMATED_VISIBILITY -> navigateToSomewhere(AnimatedVisibilityActivity::class)
        CONTENT_SIZE_ANIMATION -> navigateToSomewhere(ContentSizeAnimationActivity::class)
        GESTURE_ANIMATION -> navigateToSomewhere(GestureAnimationActivity::class)
        SIMPLE_ANIMATION -> navigateToSomewhere(SimpleAnimationActivity::class)
        TRANSITION_ANIMATION -> navigateToSomewhere(TransitionAnimationActivity::class)
        ANIMATED_SPEC_ANIMATION -> navigateToSomewhere(AnimatedSpecsAnimationActivity::class)
        ADVANCED_ANIMATION -> navigateToSomewhere(AdvancedAnimationActivity::class)
    }
}

private fun <T: Any> Context.navigateToSomewhere(activity: KClass<T>){
    startActivity(
        Intent(this, activity.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).apply {
                putExtra("parameter", 1)
            }
    )
}