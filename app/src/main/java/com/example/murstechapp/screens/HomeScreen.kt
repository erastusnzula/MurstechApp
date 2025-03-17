package com.example.murstechapp.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.murstechapp.R
import com.example.murstechapp.components.Carousel
import com.example.murstechapp.components.Featured
import com.example.murstechapp.components.MostPopular
import com.example.murstechapp.data.MutableInitialValues
import com.example.murstechapp.models.CarouselModel
import com.example.murstechapp.models.ItemModel
import com.example.murstechapp.navigation.BottomBarSection
import com.example.murstechapp.navigation.NavigationDrawerControl
import com.example.murstechapp.navigation.ScreensNav
import com.example.murstechapp.navigation.TopBarSection
import com.example.murstechapp.ui.theme.MurstechAppTheme
import kotlinx.coroutines.CoroutineScope


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    Scaffold(
        modifier = Modifier,
        topBar = { TopBarSection(scope = scope, drawerState = drawerState) },
        bottomBar = { BottomBarSection(navController = navController) },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
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
                    .padding(top=10.dp)

            )
            val allItems = CarouselModel(
                title = "Get winter Discount",
                imageUrl = R.drawable.snowy.toString(),
                target = "For children",
                discount = "20% Off"
            )
            val itemsArray = ArrayList<CarouselModel>()
            for (i in 1..5){
                itemsArray.add(allItems)
            }
            Spacer(Modifier.height(7.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                Carousel(navController = navController, carouselItems = itemsArray)
            }
            val itemsList = ItemModel(
                name = "Item 1",
                description = "Item Description",
                price = 100.00,
                quantity = 1,
                discount =2,
                imageUrl = R.drawable.cloudy_sunny.toString(),
                size = 34,
                rating =4.6,
                tag = "Shirt",
                category = "Shirts"
            )
            val listArray = ArrayList<ItemModel>()
            for (i in 1..12){
                listArray.add(itemsList)
            }
            Spacer(Modifier.height(7.dp))
            Column(
                modifier=Modifier
                    .height(300.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        text = "Featured"
                    )
                    TextButton(onClick = {
                        navController.navigate(route = ScreensNav.ItemsScreen.route)
                    }) {
                        Text(
                            text = "See All"
                        )
                    }
                }
                Row(
                    modifier=Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    Featured(featuredItems = listArray)
                }

            }
            Spacer(Modifier.height(7.dp))

            Column(
                modifier=Modifier
                    .height(300.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Most Popular"
                    )
                    TextButton(onClick = {
                        navController.navigate(route = ScreensNav.ItemsScreen.route)
                    }) {
                        Text(
                            text = "See All"
                        )
                    }
                }
                Row(
                    modifier=Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    MostPopular(mostPopularItems = listArray)
                }


            }

        }

    }

}
//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    MurstechAppTheme {
//        NavigationDrawerControl(navController = rememberNavController())
////        HomeScreen(
////            navController = rememberNavController(),
////            scope = rememberCoroutineScope(),
////            drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
////        )
//    }
//}