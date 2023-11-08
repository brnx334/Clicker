package com.brnx.clicker.navigation

sealed class Screen(val route: String){
    object MainScreen: Screen("main_screen")
    object ShopScreen: Screen("shop_screen")
    object SettingScreen: Screen("setting_screen")
}
