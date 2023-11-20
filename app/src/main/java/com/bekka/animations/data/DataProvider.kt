package com.bekka.animations.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import com.bekka.animations.data.model.AnimationModel

object DataProvider {

    const val ANIMATED_VISIBILITY = "Animated Visibility"
    const val CONTENT_SIZE_ANIMATION = "Content Size Animation"
    const val GESTURE_ANIMATION = "Gesture Animation"
    const val VALUE_BASED_ANIMATION = "Value-Based Animation"
    const val TRANSITION_ANIMATION = "Transition Animation"
    const val ANIMATED_SPEC_ANIMATION = "Animated Spec"
    const val ADVANCED_ANIMATION = "Advanced Animation"
    const val VECTOR_BASED_ANIMATION = "Vector Drawable Animation"
    const val CUSTOM_ANIMATION = "Custom Animation"
    const val SAVED_ITEMS = "Saved Items"

    val optionsList = mutableListOf(
        AnimationModel(icon = Icons.Outlined.AccountBox, option = VALUE_BASED_ANIMATION),
        AnimationModel(icon = Icons.Outlined.Favorite, option = ANIMATED_VISIBILITY),
        AnimationModel(icon = Icons.Outlined.Add, option = CONTENT_SIZE_ANIMATION),
        AnimationModel(icon = Icons.Outlined.Face, option = VECTOR_BASED_ANIMATION),
        AnimationModel(icon = Icons.Outlined.Build, option = ANIMATED_SPEC_ANIMATION),
        AnimationModel(icon = Icons.Outlined.Done, option = CUSTOM_ANIMATION),
        AnimationModel(icon = Icons.Outlined.List, option = GESTURE_ANIMATION),
        AnimationModel(icon = Icons.Outlined.AddCircle, option = TRANSITION_ANIMATION),
        AnimationModel(icon = Icons.Outlined.Clear, option = ADVANCED_ANIMATION),
        AnimationModel(icon = Icons.Outlined.Lock, option = SAVED_ITEMS),
        AnimationModel(icon = Icons.Outlined.Create, option = SAVED_ITEMS)
    )
}