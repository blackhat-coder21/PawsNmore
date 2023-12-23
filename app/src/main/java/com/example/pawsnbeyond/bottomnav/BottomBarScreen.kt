package com.example.pawsnbeyond.bottomnav

import com.example.pawsnbeyond.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
){

    object Home: BottomBarScreen(
        route = "Home",
        title = "Home",
        icon = R.drawable.outline_home_24,
        icon_focused = R.drawable.baseline_home_24
    )

    object Category: BottomBarScreen(
        route = "Category",
        title = "Category",
        icon = R.drawable.outline_category_24,
        icon_focused = R.drawable.baseline_category_24
    )

    object Consult: BottomBarScreen(
        route = "Consult",
        title = "Consult",
        icon = R.drawable.outline_cruelty_free_24,
        icon_focused = R.drawable.baseline_cruelty_free_24
    )

    object Account: BottomBarScreen(
        route = "Account",
        title = "Account",
        icon = R.drawable.outline_person_24,
        icon_focused = R.drawable.baseline_person_24
    )
}
