package com.example.murstechapp.screens

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.murstechapp.R
import com.example.murstechapp.data.MutableInitialValues
import com.example.murstechapp.models.Products
import com.example.murstechapp.navigation.ScreensNav
import com.example.murstechapp.ui.theme.MurstechAppTheme

@Composable
fun ItemsScreen(modifier: Modifier=Modifier, navController: NavController) {
    val width = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current
    val sample = Products(
        id = "1",
        title = "Item One",
        description = "Description",
        category = "Category",
        price = 34.4,
        discountPercentage = 20.09,
        rating = 4.5,
        stock = 200,
        brand = "brand",
        sku = "Sku",
        weight = 23,
        images = listOf("gfg"),
        thumbnail = "",
        reviews = listOf()
    )
    val sampleList = ArrayList<Products>()
    for (i in 1..12){
        sampleList.add(sample)
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier=Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 17.5.dp, start = 17.5.dp)
                .fillMaxWidth()
        ) {
            IconButton(onClick = {
                navController.navigate(route = ScreensNav.HomeScreen.route)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary

                )

            }
            Text(
                text = "All Products (${MutableInitialValues.allItemsApi.value.size})",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(start = width/2- 120.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = width/2 - 34.dp),
            modifier = Modifier
                .padding(17.5.dp),
            state = rememberLazyGridState(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            items(
                items = MutableInitialValues.allItemsApi.value,
                itemContent = {
                    Card(
                        onClick = {
                            try{
                                val products = ArrayList<Products>()
                                products.add(it)
                                navController.currentBackStackEntry?.savedStateHandle?.set("products", products)
                                navController.navigate(route = ScreensNav.ItemScreen.route)
                            }catch (e: Exception){
                                Toast.makeText(context, e.message.toString(), Toast.LENGTH_LONG).show()
                            }
                        },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        modifier = Modifier
                            .height(200.dp)
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier=Modifier
                                .fillMaxWidth()
                                .height(110.dp)
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

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Text(
                                text = it.title,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()

                            ) {
                                Text(
                                    text = "$" + it.price.toString()
                                )
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

@Preview
@Composable
fun Pv(){
    MurstechAppTheme {
        ItemsScreen(navController= rememberNavController())
    }
}