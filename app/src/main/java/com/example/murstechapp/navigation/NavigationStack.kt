package com.example.murstechapp.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.murstechapp.models.AuthModel
import com.example.murstechapp.screens.CartScreen
import com.example.murstechapp.screens.ContactScreen
import com.example.murstechapp.screens.HelpScreen
import com.example.murstechapp.screens.HomeScreen
import com.example.murstechapp.screens.ItemScreen
import com.example.murstechapp.screens.ItemsScreen
import com.example.murstechapp.screens.ProfileScreen
import com.example.murstechapp.screens.SearchScreen
import com.example.murstechapp.screens.SettingsScreen
import com.example.murstechapp.screens.ShareScreen
import com.example.murstechapp.screens.SignInScreen
import com.example.murstechapp.screens.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationStack(authModel: AuthModel) {
    val firebaseAuth = FirebaseAuth.getInstance()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if(firebaseAuth.currentUser != null) ScreensNav.HomeScreen.route else ScreensNav.SignInScreen.route
    ){

        composable(route = ScreensNav.HomeScreen.route){
            HomeScreen(
                navController = navController,
                authModel = authModel
            )
        }
        composable(route=ScreensNav.SettingsScreen.route){
            SettingsScreen(navController = navController)
        }
        composable(route = ScreensNav.ProfileScreen.route){
            ProfileScreen(navController=navController)
        }
        composable(route=ScreensNav.ContactScreen.route){
            ContactScreen(navController=navController)
        }
        composable(route=ScreensNav.CartScreen.route){
            CartScreen(navController=navController)
        }
        composable(route=ScreensNav.ItemScreen.route){
            ItemScreen(navController=navController)
        }
        composable(route=ScreensNav.ItemsScreen.route){
            ItemsScreen(navController=navController)
        }
        composable(route=ScreensNav.ShareScreen.route){
            ShareScreen(navController=navController)
        }
        composable(route=ScreensNav.HelpScreen.route){
            HelpScreen(navController=navController)
        }
        composable(route=ScreensNav.SignInScreen.route){
            SignInScreen(navController=navController, authModel = authModel)
        }
        composable(route=ScreensNav.SignUpScreen.route){
            SignUpScreen(navController=navController, authModel)
        }
        composable(route=ScreensNav.SearchScreen.route){
            SearchScreen(navController=navController)
        }
    }

}