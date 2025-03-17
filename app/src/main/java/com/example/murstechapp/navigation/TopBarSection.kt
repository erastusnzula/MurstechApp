package com.example.murstechapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.murstechapp.R
import com.example.murstechapp.screens.HomeScreen
import com.example.murstechapp.ui.theme.MurstechAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSection(openDrawer: ()->Unit){
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
               onClick = openDrawer,
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
           containerColor = MaterialTheme.colorScheme.surfaceContainer,
           titleContentColor = MaterialTheme.colorScheme.secondary,
           scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
           actionIconContentColor = MaterialTheme.colorScheme.secondary,
           navigationIconContentColor = MaterialTheme.colorScheme.secondary,


       ),
//       scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
   )

}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview(){
//    MurstechAppTheme {
//        //TopBarSection()
//        //HomeScreen(navController = rememberNavController())
//        NavigationDrawerControl(navController = rememberNavController())
//    }
//}