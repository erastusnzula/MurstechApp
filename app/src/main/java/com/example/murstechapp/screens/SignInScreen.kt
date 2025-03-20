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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
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
fun SignInScreen(navController: NavController, authModel: AuthModel) {
    val authenticationStatus = authModel.authenticationStatus.observeAsState()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }



    LaunchedEffect(authenticationStatus.value) {
        when (authenticationStatus.value) {
            is UserStatus.Authenticated -> {
                navController.navigate(route = ScreensNav.HomeScreen.route)
            }

            is UserStatus.AuthenticationError -> {
                MutableInitialValues.signInError.value =
                    (authenticationStatus.value as UserStatus.AuthenticationError).message
            }

            else -> Unit
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

//        Text(
//            text = "Hi , ERASTUS",
//            style = MaterialTheme.typography.displaySmall
//        )

        Text(
            fontSize = 20.sp,
            text = "Welcome Back!",
            color = MaterialTheme.colorScheme.secondary
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
                        text = "Sign in With Google",
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
            text = MutableInitialValues.signInError.value,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email.value,
            onValueChange = { email.value = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
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
            onValueChange = { password.value = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        showPassword.value = !showPassword.value
                    }
                ) {
                    Icon(
                        imageVector = if (showPassword.value) {
                            Icons.Filled.Visibility
                        } else {
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
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextButton(
                onClick = {},

                ) {
                Text(
                    text = "Forgot password",
                    textAlign = TextAlign.End
                )
            }
        }
        Button(
            onClick = {
                authModel.signIn(email.value, password.value)
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Sign in",
                modifier = Modifier,
                style = MaterialTheme.typography.titleLarge
            )
        }
        TextButton(
            onClick = {
                MutableInitialValues.signUpError.value = ""
                navController.navigate(route = ScreensNav.SignUpScreen.route)
            },

            ) {
            Text(
                text = "Don't have an account? Sign Up"
            )
        }


    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignInPreview(){
//    MurstechAppTheme {
//        SignInScreen(navController = rememberNavController())
//    }
//
//}