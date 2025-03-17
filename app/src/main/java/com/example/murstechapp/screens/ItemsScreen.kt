package com.example.murstechapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.murstechapp.R
import com.example.murstechapp.models.ItemModel
import com.example.murstechapp.ui.theme.MurstechAppTheme

@Composable
fun ItemsScreen(modifier: Modifier=Modifier, navController: NavController) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(17.5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy((LocalConfiguration.current.screenWidthDp.dp/2)-(LocalConfiguration.current.screenWidthDp.dp/4)),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
            Text("Items")
        }
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth(),
            columns = GridCells.Fixed(
                count = 2
            )
        ) {
            items(listArray){item->
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier=Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(item.imageUrl.toInt()),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier=Modifier
                            .width(LocalConfiguration.current.screenWidthDp.dp/2)
                    )
                    Text(
                        text = item.name
                    )
                    Text(
                        text = item.price.toString()
                    )
                }

            }
        }

    }

}
@Preview(showBackground = true)
@Composable
fun Prevvv(){
    MurstechAppTheme {
        ItemsScreen(navController = rememberNavController())
    }
}