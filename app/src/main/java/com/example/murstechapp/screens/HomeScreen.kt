package com.example.murstechapp.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.murstechapp.R
import com.example.murstechapp.api.ApiCall
import com.example.murstechapp.data.MutableInitialValues
import com.example.murstechapp.models.AuthModel
import com.example.murstechapp.models.CarouselModel
import com.example.murstechapp.models.Products
import com.example.murstechapp.models.UserStatus
import com.example.murstechapp.navigation.ScreensNav
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, authModel: AuthModel) {
    val authenticationStatus = authModel.authenticationStatus.observeAsState()
    LaunchedEffect(authenticationStatus.value) {
        when (authenticationStatus.value) {
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
    ApiCall()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val firebase = FirebaseAuth.getInstance()
    MutableInitialValues.currentUser.value = firebase.currentUser?.email.toString()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val imageURL = remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        imageURL.value = it
    }

    val allItemsApi = MutableInitialValues.allItemsApi.value
    val allItemsApiMostPopular = MutableInitialValues.allItemsApiMostPopular.value
    val allItemsApiElectronics = MutableInitialValues.allItemsApiElectronics.value
    val allCarousels = MutableInitialValues.allItemsApiCarousel.value

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier,
                drawerShape = RoundedCornerShape(10),
                drawerContainerColor = MaterialTheme.colorScheme.primaryContainer,
                drawerContentColor = MaterialTheme.colorScheme.secondary,
            ) {
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


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("")
                        IconButton(
                            modifier = Modifier,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                tint = MaterialTheme.colorScheme.error,
                                contentDescription = "",
                                modifier = Modifier

                            )
                        }

                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {


                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            if (imageURL.value != null) {
                                imageURL.value?.let {
                                    if (Build.VERSION.SDK_INT < 28) {
                                        bitmap.value = MediaStore.Images
                                            .Media.getBitmap(context.contentResolver, it)

                                    } else {
                                        val source = ImageDecoder
                                            .createSource(context.contentResolver, it)
                                        bitmap.value = ImageDecoder.decodeBitmap(source)
                                    }


                                    IconButton(
                                        modifier = Modifier
                                            .size(150.dp)
                                            .clip(CircleShape),
                                        onClick = { launcher.launch("image/*") }
                                    ) {
                                        if (bitmap.value != null) {
                                            bitmap.value?.let {
                                                MutableInitialValues.profileImage.value =
                                                    it.asImageBitmap()
                                                Image(
                                                    bitmap = it.asImageBitmap(),
                                                    contentScale = ContentScale.Crop,
                                                    contentDescription = "",
                                                    modifier = Modifier

                                                )
                                            }
                                        } else {
                                            Icon(
                                                imageVector = Icons.Filled.AccountCircle,
                                                tint = Color.Unspecified,
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .fillMaxSize()
                                            )
                                        }


                                    }

                                }
                            } else {
                                IconButton(
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(50)),
                                    onClick = { launcher.launch("image/*") }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.AccountCircle,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .fillMaxSize()

                                    )
                                }


                            }

                            IconButton(onClick = {
                                launcher.launch("image/*")
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.AttachFile,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }


                        }

                        Text(
                            text = MutableInitialValues.currentUserName.value,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = MutableInitialValues.currentUser.value
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
                        onClick = { navController.navigate(route = ScreensNav.ProfileScreen.route) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        icon = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Filled.Person,
                                        contentDescription = ""
                                    )
                                    Text(
                                        text = "Profile",
                                        modifier = Modifier
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
                            unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                                .1f
                            ),
                            selectedContainerColor = MaterialTheme.colorScheme.surfaceContainer
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
                        onClick = { navController.navigate(route = ScreensNav.SettingsScreen.route) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        icon = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Filled.Settings,
                                        contentDescription = ""
                                    )
                                    Text(
                                        text = "Settings",
                                        modifier = Modifier
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
                            unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                                .1f
                            ),
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
                        onClick = { navController.navigate(route = ScreensNav.ContactScreen.route) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        icon = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Filled.MailOutline,
                                        contentDescription = ""
                                    )
                                    Text(
                                        text = "Contact",
                                        modifier = Modifier
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
                            unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                                .1f
                            ),
                            selectedContainerColor = MaterialTheme.colorScheme.surfaceContainer
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
                        onClick = { navController.navigate(route = ScreensNav.ShareScreen.route) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        icon = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Filled.Share,
                                        contentDescription = ""
                                    )
                                    Text(
                                        text = "Share App",
                                        modifier = Modifier
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
                            unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                                .1f
                            ),
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
                        onClick = { navController.navigate(route = ScreensNav.HelpScreen.route) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        icon = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Filled.Info,
                                        contentDescription = ""
                                    )
                                    Text(
                                        text = "Help",
                                        modifier = Modifier
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
                            unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                                .1f
                            ),
                            selectedContainerColor = MaterialTheme.colorScheme.primary
                        ),
                    )
                    Button(
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 20.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            authModel.logOut()
                            navController.navigate(route=ScreensNav.SignInScreen.route)
                        },
                    ) {
                        Text(
                            text = "Sign Out"
                        )
                    }


                }

            }

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
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                if (MutableInitialValues.profileImage.value != null) {
                                    Image(
                                        bitmap = MutableInitialValues.profileImage.value!!,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(shape = CircleShape)

                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Filled.AccountCircle,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(shape = CircleShape)
                                    )
                                }

                            }
                            Text(
                                text = MutableInitialValues.currentUser.value,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                    },
                    modifier = Modifier,
//                    navigationIcon = {
//                        IconButton(
//                            onClick = {
//                                scope.launch {
//                                    drawerState.apply {
//                                        if (isClosed) open() else close()
//                                    }
//                                }
//                            },
//                            modifier=Modifier
//                                .clip(RoundedCornerShape(50))
//                        ) {
//                            Icon(
//                                imageVector = Icons.Filled.AccountCircle,
//                                contentDescription = "",
//                                modifier = Modifier
//
//                            )
//                        }
//
//                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(30.dp)

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
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(30.dp)
                                )
                            }
                            IconButton(
                                onClick = { navController.navigate(route = ScreensNav.SearchScreen.route) }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(30.dp)
                                )
                            }
                            IconButton(
                                onClick = { navController.navigate(route = ScreensNav.CartScreen.route) }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ShoppingCart,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(30.dp)
                                )
                            }
                            IconButton(
                                onClick = { navController.navigate(route = ScreensNav.ProfileScreen.route) }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(30.dp)
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
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedBorderColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.surface,

                        ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)

                )

                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                ) {
                    items(
                        items = allCarousels,
                        itemContent = { item ->
                            ElevatedCard(
                                onClick = {
                                    try{
                                        val products = ArrayList<Products>()
                                        products.add(item)
                                        navController.currentBackStackEntry?.savedStateHandle?.set("products", products)
                                        navController.navigate(route = ScreensNav.ItemScreen.route)
                                    }catch (e: Exception){
                                        Toast.makeText(context, e.message.toString(), Toast.LENGTH_LONG).show()
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .width(screenWidth - 17.5.dp)
                                    .height(120.dp)
                                    .padding(top = 8.dp)
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                elevation = CardDefaults.elevatedCardElevation(
                                    defaultElevation = 16.dp,
                                )
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .fillMaxHeight(),

                                        ) {
                                        Text(
                                            text = MutableInitialValues.carouselTitle.value
                                        )
                                        Text(
                                            text = item.discountPercentage.toString() + MutableInitialValues.carouselDiscount.value
                                        )
                                        Text(
                                            text = item.title,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Image(
                                        painter = rememberAsyncImagePainter(item.thumbnail),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(16.dp))
                                    )

                                }

                            }

                        }
                    )
                }

                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    items(
                        items = allCarousels,
                        itemContent = {

                            Icon(
                                imageVector = Icons.Filled.Circle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(15.dp)
                            )


                        }
                    )

                }

                if (MutableInitialValues.error.value.isNotEmpty()){
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = MutableInitialValues.error.value
                    )
                    Spacer(Modifier.height(8.dp))
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = MutableInitialValues.featured.value
                    )
                    TextButton(onClick = {
                        navController.navigate(route = ScreensNav.ItemsScreen.route)
                    }) {
                        Text(
                            text = MutableInitialValues.seeAll.value
                        )
                    }

                }
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                ) {
                    items(
                        items = allItemsApi,
                        itemContent = {
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                modifier = Modifier
                                    .width(screenWidth / 3)
                                    .height(150.dp),
                                onClick = {
                                    try{
                                        val products = ArrayList<Products>()
                                        products.add(it)
                                        navController.currentBackStackEntry?.savedStateHandle?.set("products", products)
                                        navController.navigate(route = ScreensNav.ItemScreen.route)
                                    }catch (e: Exception){
                                        Toast.makeText(context, e.message.toString(), Toast.LENGTH_LONG).show()
                                    }

                                }) {
                                Column {
//                                    Image(
//                                        contentScale = ContentScale.FillBounds,
//                                        painter = rememberAsyncImagePainter(it.thumbnail),
//                                        contentDescription = null,
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(90.dp)
//                                    )
                                    Column(
                                        modifier=Modifier
                                            .fillMaxWidth()
                                            .height(90.dp)
                                    ) {
                                        Column(
                                            modifier=Modifier
                                                .fillMaxWidth()
                                                .paint(
                                                    painter = rememberAsyncImagePainter(it.thumbnail),
                                                    contentScale = ContentScale.FillBounds
                                                )
                                                .fillMaxHeight(1f)
                                        ) {
                                            IconButton(
                                                modifier = Modifier
                                                    .align(Alignment.End),
                                                onClick = {}
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.FavoriteBorder,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.secondary
                                                )
                                            }
                                        }
                                    }

                                    Text(
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        text = it.title,
                                        modifier = Modifier
                                            .padding(start = 10.dp)
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Bottom,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        TextButton(onClick = {}) {
                                            Text(
                                                text = "$" + it.price.toString(),
                                            )
                                        }
                                        TextButton(onClick = {}) {
                                            Text(
                                                text = "View"
                                            )
                                        }
                                    }
                                }
                            }

                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = MutableInitialValues.mostPopular.value
                    )
                    TextButton(onClick = {
                        navController.navigate(route = ScreensNav.ItemsScreen.route)
                    }) {
                        Text(
                            text = MutableInitialValues.seeAll.value
                        )
                    }

                }
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                ) {
                    items(
                        items = allItemsApiMostPopular,
                        itemContent = {
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                modifier = Modifier
                                    .width(screenWidth / 3)
                                    .height(150.dp),
                                onClick = {
                                    try{
                                        val products = ArrayList<Products>()
                                        products.add(it)
                                        navController.currentBackStackEntry?.savedStateHandle?.set("products", products)
                                        navController.navigate(route = ScreensNav.ItemScreen.route)
                                    }catch (e: Exception){
                                        Toast.makeText(context, e.message.toString(), Toast.LENGTH_LONG).show()
                                    }
                                }) {
                                Column {
                                    Column(
                                        modifier=Modifier
                                            .fillMaxWidth()
                                            .height(90.dp)
                                    ) {
                                        Column(
                                            modifier=Modifier
                                                .fillMaxWidth()
                                                .paint(
                                                    painter = rememberAsyncImagePainter(it.thumbnail),
                                                    contentScale = ContentScale.FillBounds
                                                )
                                                .fillMaxHeight(1f)
                                        ) {
                                            IconButton(
                                                modifier = Modifier
                                                    .align(Alignment.End),
                                                onClick = {}
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.FavoriteBorder,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.secondary
                                                )
                                            }
                                        }
                                    }

                                    Text(
                                        overflow = TextOverflow.Ellipsis,
                                        text = it.title,
                                        maxLines = 1,
                                        modifier = Modifier
                                            .padding(start = 10.dp)
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Bottom,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        TextButton(onClick = {}) {
                                            Text(
                                                text = "$"+it.price.toString(),
                                            )
                                        }
                                        TextButton(onClick = {}) {
                                            Text(
                                                text = "View"
                                            )
                                        }
                                    }
                                }
                            }

                        }
                    )
                }


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = MutableInitialValues.electronics.value
                    )
                    TextButton(onClick = {
                        navController.navigate(route = ScreensNav.ItemsScreen.route)
                    }) {
                        Text(
                            text = MutableInitialValues.seeAll.value
                        )
                    }

                }
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                ) {
                    items(
                        items = allItemsApiElectronics,
                        itemContent = {
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                modifier = Modifier
                                    .width(screenWidth / 3)
                                    .height(150.dp),
                                onClick = {
                                    try{
                                        val products = ArrayList<Products>()
                                        products.add(it)
                                        navController.currentBackStackEntry?.savedStateHandle?.set("products", products)
                                        navController.navigate(route = ScreensNav.ItemScreen.route)
                                    }catch (e: Exception){
                                        Toast.makeText(context, e.message.toString(), Toast.LENGTH_LONG).show()
                                    }
                                }) {
                                Column {
                                    Column(
                                        modifier=Modifier
                                            .fillMaxWidth()
                                            .height(90.dp)
                                    ) {
                                        Column(
                                            modifier=Modifier
                                                .fillMaxWidth()
                                                .paint(
                                                    painter = rememberAsyncImagePainter(it.thumbnail),
                                                    contentScale = ContentScale.FillBounds
                                                )
                                                .fillMaxHeight(1f)
                                        ) {
                                            IconButton(
                                                modifier = Modifier
                                                    .align(Alignment.End),
                                                onClick = {}
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.FavoriteBorder,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.secondary
                                                )
                                            }
                                        }
                                    }

                                    Text(
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        text = it.title,
                                        modifier = Modifier
                                            .padding(start = 10.dp)
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Bottom,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        TextButton(onClick = {}) {
                                            Text(
                                                text = "$"+it.price.toString(),
                                            )
                                        }
                                        TextButton(onClick = {}) {
                                            Text(
                                                text = "View"
                                            )
                                        }
                                    }
                                }
                            }

                        }
                    )
                }


            }

        }

    }
}

@Composable
fun ViewItem(){

}