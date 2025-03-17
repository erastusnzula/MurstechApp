package com.example.murstechapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.murstechapp.R
import com.example.murstechapp.data.MutableInitialValues
import com.example.murstechapp.models.AuthModel
import com.example.murstechapp.models.CarouselModel
import com.example.murstechapp.models.ItemModel
import com.example.murstechapp.models.UserStatus
import com.example.murstechapp.navigation.DrawerContentSection
import com.example.murstechapp.navigation.ScreensNav
import com.example.murstechapp.ui.theme.MurstechAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, authModel: AuthModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val authenticationStatus = authModel.authenticationStatus.observeAsState()
    LaunchedEffect(authenticationStatus.value) {
        when(authenticationStatus.value){
            is UserStatus.UnAuthenticated -> {
                navController.navigate(route = ScreensNav.SignInScreen.route)
            }

            is UserStatus.AuthenticationError -> {
                MutableInitialValues.signInError.value =
                    (authenticationStatus.value as UserStatus.AuthenticationError).message
            }
            else -> Unit
        }
    }

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContentSection(navController = navController, authModel=authModel)
        },
        modifier = Modifier,
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = MaterialTheme.colorScheme.surfaceContainer
    ) {
        Scaffold(
            modifier = Modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("Hello")
                            Text(
                                text = "Erastus Nzula",
                                fontWeight = FontWeight.Medium
                            )
                        }

                    },
                    modifier = Modifier,
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            },
                            modifier=Modifier
                                .size(60.dp).clip(RoundedCornerShape(50))
                                .background(
                                    MaterialTheme.colorScheme.surface
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(60.dp)

                            )
                        }

                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = ""
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.secondary,
                        scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.secondary,
                        navigationIconContentColor = MaterialTheme.colorScheme.secondary,


                        ),
                )
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
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
            },
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary,
        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(8.dp)

            ) {
                Spacer(Modifier.height(7.dp))
                OutlinedTextField(
                    value = MutableInitialValues.search.value,
                    onValueChange = { MutableInitialValues.search.value = it },
                    singleLine = true,
                    maxLines = 1,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = ""
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Search here"
                        )
                    },
                    shape = RoundedCornerShape(30),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
                        focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,

                        ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)

                )

            }

        }

    }
}

