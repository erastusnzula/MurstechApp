package com.example.murstechapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.murstechapp.R
import com.example.murstechapp.models.CarouselModel
import com.example.murstechapp.ui.theme.MurstechAppTheme
import okhttp3.internal.format
import okhttp3.internal.wait


@Composable

fun Carousel(
    modifier: Modifier = Modifier,
    navController: NavController,
    carouselItems: ArrayList<CarouselModel>
) {


    carouselItems.forEach {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10))
                .background(MaterialTheme.colorScheme.primaryContainer.copy(.9f))
                .width(LocalConfiguration.current.screenWidthDp.dp - 34.dp)
                .height(150.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(7.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = it.title
                )
                Text(
                    text = it.discount
                )
                Text(
                    text = it.target
                )
            }
            Image(
                painter = painterResource(it.imageUrl.toInt()),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 7.dp)
            )


        }
        Spacer(Modifier.width(7.dp))
    }


}




