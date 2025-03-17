package com.example.murstechapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.murstechapp.models.ItemModel

@Composable
fun Featured(modifier: Modifier=Modifier, featuredItems:ArrayList<ItemModel>) {
    featuredItems.forEach {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp/2)
                .clip(shape = RoundedCornerShape(10))
                .background(MaterialTheme.colorScheme.primaryContainer.copy(.9f))
        ) {

            Column(
                modifier = Modifier
                    .padding(7.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(it.imageUrl.toInt()),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(end = 7.dp)
                )
                Text(
                    text = it.name
                )
                Text(
                    text = it.price.toString()
                )
            }



        }
        Spacer(Modifier.width(7.dp))
    }
}