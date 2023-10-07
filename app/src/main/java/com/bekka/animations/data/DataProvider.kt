package com.bekka.animations.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Notifications
import com.bekka.animations.data.model.AnimationModel

object DataProvider {

    val optionsList = mutableListOf(
        AnimationModel(icon = Icons.Outlined.Favorite, option = "Saved Items"),
        AnimationModel(icon = Icons.Outlined.Notifications, option = "Previous Notifications"),
        AnimationModel(icon = Icons.Outlined.Favorite, option = "Saved Items"),
        AnimationModel(icon = Icons.Outlined.Notifications, option = "Previous Notifications"),
        AnimationModel(icon = Icons.Outlined.Favorite, option = "Saved Items"),
        AnimationModel(icon = Icons.Outlined.Notifications, option = "Previous Notifications"),
        AnimationModel(icon = Icons.Outlined.Favorite, option = "Saved Items"),
        AnimationModel(icon = Icons.Outlined.Notifications, option = "Previous Notifications"),
        AnimationModel(icon = Icons.Outlined.Favorite, option = "Saved Items"),
        AnimationModel(icon = Icons.Outlined.Notifications, option = "Previous Notifications")
    )
}