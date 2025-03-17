package com.example.murstechapp.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.murstechapp.ui.theme.MurstechAppTheme

@Composable
fun BottomBarSection(modifier: Modifier = Modifier, navController: NavController){
    BottomAppBar(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.secondary,
        tonalElevation = 0.dp,
        contentPadding = BottomAppBarDefaults.ContentPadding,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = {navController.navigate(route = ScreensNav.SearchScreen.route)}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = {navController.navigate(route = ScreensNav.CartScreen.route)}
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = {navController.navigate(route = ScreensNav.ProfileScreen.route)}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = ""
                    )
                }
            }

        }
    )

}


//@Preview(showBackground = true)
//@Composable
//fun BottomPreview(){
//    MurstechAppTheme {
//        NavigationDrawerControl(navController = rememberNavController())
//    }
//}