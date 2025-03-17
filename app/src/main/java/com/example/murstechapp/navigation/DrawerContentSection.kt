package com.example.murstechapp.navigation

import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.murstechapp.R
import com.example.murstechapp.ui.theme.MurstechAppTheme


@Composable
fun DrawerContentSection( navController: NavController, signOut:()->Unit){
    ModalDrawerSheet(
        modifier = Modifier,
        drawerShape = RoundedCornerShape(10),
        drawerContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        drawerContentColor = MaterialTheme.colorScheme.secondary,
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 17.5.dp,
                    top = 28.dp,
                    end = 17.5.dp,
                    bottom = 28.dp
                )

        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier=Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(50))
                        .align(Alignment.CenterHorizontally)
                    ,
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        tint = Color.Unspecified,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()

                    )
                }
                Text(
                    text = "Erastus Nzula",
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "nzulaerastus@gmail.com"
                )

            }

            Spacer(Modifier.height(7.dp))
            NavigationDrawerItem(
                label = {
                    Text(
                        text = ""
                    )
                },
                selected = false,
                onClick = {navController.navigate(route = ScreensNav.ProfileScreen.route)},
                modifier = Modifier
                    .fillMaxWidth(),
                icon = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier=Modifier
                            .fillMaxWidth()
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = ""
                            )
                            Text(
                                text = "Profile",
                                modifier=Modifier
                                    .padding(start = 7.dp)
                            )

                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = ""
                        )
                    }

                },
                shape = RoundedCornerShape(30),
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(.1f),
                    selectedContainerColor = MaterialTheme.colorScheme.primary
                ),
            )
            Spacer(Modifier.height(7.dp))
            NavigationDrawerItem(
                label = {
                    Text(
                        text = ""
                    )
                },
                selected = false,
                onClick = {navController.navigate(route = ScreensNav.SettingsScreen.route)},
                modifier = Modifier
                    .fillMaxWidth(),
                icon = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier=Modifier
                            .fillMaxWidth()
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = ""
                            )
                            Text(
                                text = "Settings",
                                modifier=Modifier
                                    .padding(start = 7.dp)
                            )

                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = ""
                        )
                    }

                },
                shape = RoundedCornerShape(30),
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(.1f),
                    selectedContainerColor = MaterialTheme.colorScheme.primary
                ),
            )
            Spacer(Modifier.height(7.dp))
            NavigationDrawerItem(
                label = {
                    Text(
                        text = ""
                    )
                },
                selected = true,
                onClick = {navController.navigate(route = ScreensNav.ContactScreen.route)},
                modifier = Modifier
                    .fillMaxWidth(),
                icon = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier=Modifier
                            .fillMaxWidth()
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.MailOutline,
                                contentDescription = ""
                            )
                            Text(
                                text = "Contact",
                                modifier=Modifier
                                    .padding(start = 7.dp)
                            )

                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = ""
                        )
                    }

                },
                shape = RoundedCornerShape(30),
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(.1f),
                    selectedContainerColor = MaterialTheme.colorScheme.primary.copy(.8f)
                ),
            )
            Spacer(Modifier.height(7.dp))
            NavigationDrawerItem(
                label = {
                    Text(
                        text = ""
                    )
                },
                selected = false,
                onClick = {navController.navigate(route = ScreensNav.ShareScreen.route)},
                modifier = Modifier
                    .fillMaxWidth(),
                icon = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier=Modifier
                            .fillMaxWidth()
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = ""
                            )
                            Text(
                                text = "Share App",
                                modifier=Modifier
                                    .padding(start = 7.dp)
                            )

                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = ""
                        )
                    }

                },
                shape = RoundedCornerShape(30),
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(.1f),
                    selectedContainerColor = MaterialTheme.colorScheme.primary
                ),
            )
            Spacer(Modifier.height(7.dp))
            NavigationDrawerItem(
                label = {
                    Text(
                        text = ""
                    )
                },
                selected = false,
                onClick = {navController.navigate(route = ScreensNav.HelpScreen.route)},
                modifier = Modifier
                    .fillMaxWidth(),
                icon = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier=Modifier
                            .fillMaxWidth()
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = ""
                            )
                            Text(
                                text = "Help",
                                modifier=Modifier
                                    .padding(start = 7.dp)
                            )

                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = ""
                        )
                    }

                },
                shape = RoundedCornerShape(30),
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(.1f),
                    selectedContainerColor = MaterialTheme.colorScheme.primary
                ),
            )
            Button(onClick = signOut) {
                Text(
                    text = "Sign Out"
                )
            }


        }

    }

}

//@Preview(showBackground = true)
//@Composable
//fun previ(){
//    MurstechAppTheme {
//        DrawerContentSection(navController = rememberNavController())
//    }
//}