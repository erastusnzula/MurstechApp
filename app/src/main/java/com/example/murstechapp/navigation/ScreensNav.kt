package com.example.murstechapp.navigation

sealed class ScreensNav(val route: String) {
    data object HomeScreen: ScreensNav(route = "home-screen")
    data object SettingsScreen: ScreensNav(route = "settings-screen")
    data object SignInScreen: ScreensNav(route = "sign-in-screen")
    data object SignUpScreen: ScreensNav(route = "sign-Up-screen")
    data object ContactScreen: ScreensNav(route = "contact-screen")
    data object CartScreen: ScreensNav(route = "cart-screen")
    data object CheckoutScreen: ScreensNav(route = "checkout-screen")
    data object HelpScreen: ScreensNav(route = "help-screen")
    data object ItemScreen: ScreensNav(route = "item-screen")
    data object ItemsScreen: ScreensNav(route = "items-screen")
    data object OrderScreen: ScreensNav(route = "order-screen")
    data object SearchScreen: ScreensNav(route = "search-screen")
    data object ProfileScreen: ScreensNav(route = "profile-screen")
    data object ShareScreen: ScreensNav(route = "share-screen")
}