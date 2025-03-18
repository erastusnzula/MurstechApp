package com.example.murstechapp.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.murstechapp.R
import com.example.murstechapp.data.MutableInitialValues
import com.example.murstechapp.models.AuthModel
import com.example.murstechapp.models.UserStatus
import com.example.murstechapp.navigation.ScreensNav
import com.example.murstechapp.ui.theme.MurstechAppTheme

@Composable
fun SignUpScreen(navController: NavController, authModel: AuthModel) {
    val authenticationStatus = authModel.authenticationStatus.observeAsState()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirm = remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }


    LaunchedEffect(authenticationStatus.value) {
        when(authenticationStatus.value){
            is UserStatus.Authenticated ->{
                navController.navigate(route = ScreensNav.HomeScreen.route)
            }
            is UserStatus.AuthenticationError -> {
                MutableInitialValues.signUpError.value =
                    (authenticationStatus.value as UserStatus.AuthenticationError).message
            }
            else-> Unit
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.AccountBox,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(150.dp)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            fontSize = 20.sp,
            text = "Welcome!"
        )
        Spacer(Modifier.height(16.dp))
        OutlinedButton(
            onClick = {
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ){
                Icon(
                    painter = painterResource(R.drawable.google_icon),
                    contentDescription = null,
                    tint = Color.Unspecified

                )
                Text(
                    text = "Sign up With Google",
                    modifier = Modifier
                        .padding(start = 10.dp),
                    style = MaterialTheme.typography.titleMedium,

                    )

            }

        }
        Spacer(Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HorizontalDivider(Modifier.width(
                LocalConfiguration.current.screenWidthDp.dp/3
            ))
            Text(
                fontSize = 15.sp,
                text = "or"
            )
            HorizontalDivider(Modifier.width(
                LocalConfiguration.current.screenWidthDp.dp/3
            ))
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = MutableInitialValues.signUpError.value
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email.value,
            onValueChange = {email.value = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(
                    text = "Enter your email address"
                )
            }
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password.value,
            onValueChange = {password.value = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            visualTransformation = if(showPassword.value){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()

            },
            trailingIcon = {
                IconButton(
                    onClick = {showPassword.value = !showPassword.value
                    }
                ) {
                    Icon(
                        imageVector = if (showPassword.value){
                            Icons.Filled.Visibility
                        }else{
                            Icons.Filled.VisibilityOff
                        },
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }

            },
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(
                    text = "Enter your password"
                )
            }
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordConfirm.value,
            onValueChange = {passwordConfirm.value = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            visualTransformation = if(showPassword.value){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = {showPassword.value = !showPassword.value
                    }
                ) {
                    Icon(
                        imageVector = if (showPassword.value){
                            Icons.Filled.Visibility
                        }else{
                            Icons.Filled.VisibilityOff
                        },
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }

            },
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(
                    text = "Re-Enter your password"
                )
            }
        )
        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            authModel.signUp(email.value, password.value, passwordConfirm.value
            )
        },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Sign Up",
                modifier = Modifier,
                style = MaterialTheme.typography.titleLarge
            )
        }
        TextButton(
            onClick = {
                MutableInitialValues.signInError.value = ""
                navController.navigate(route = ScreensNav.SignInScreen.route) },

            ) {
            Text(
                text = "Already have an account? Sign In"
            )
        }



    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignUpPrev(){
//    MurstechAppTheme {
//        SignUpScreen(navController = rememberNavController())
//    }
//}