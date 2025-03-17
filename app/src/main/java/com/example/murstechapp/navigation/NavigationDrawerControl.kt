package com.example.murstechapp.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.murstechapp.screens.HomeScreen

@Composable
fun NavigationDrawerControl(modifier: Modifier=Modifier, navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = { DrawerContentSection(navController = navController) },
        modifier = Modifier,
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        HomeScreen(navController =navController, scope = scope, drawerState = drawerState )
    }
}