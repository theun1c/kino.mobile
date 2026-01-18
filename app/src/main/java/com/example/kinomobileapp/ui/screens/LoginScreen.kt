package com.example.kinomobileapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kinomobileapp.ui.components.BasicButton
import com.example.kinomobileapp.ui.components.BasicTextField
import com.example.kinomobileapp.R
import com.example.kinomobileapp.ui.viewmodels.AuthViewModel

@Composable
fun LoginScreen(
   navController: NavController = rememberNavController(),
   viewModel: AuthViewModel = hiltViewModel(),
   onLoginSuccess: () -> Unit = { navController.navigate("movie_catalog") }
){

   var email by remember { mutableStateOf("") }
   var password by remember { mutableStateOf("") }

   val isLoading by viewModel.isLoading.collectAsState()
   val error by viewModel.error.collectAsState()
   val loginSuccess by viewModel.loginSuccess.collectAsState()

   var isFormValid = email.isNotEmpty() && password.isNotEmpty()

   LaunchedEffect(loginSuccess) {
      if(loginSuccess){
         onLoginSuccess
      }
   }

   LaunchedEffect(Unit) {
      viewModel.resetState()
   }

   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(32.dp),
      horizontalAlignment = Alignment.CenterHorizontally,

   ) {
      // icon
      Image(
         painter = painterResource(id = R.drawable.ic_launcher_foreground),
         contentDescription = "image logo",
         contentScale = ContentScale.Fit,
         modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
      )

      // 2 TF
      BasicTextField(
         label = "Логин",
         placeholder = "Введите почту",
         value = email,
         onValueChange = { email = it },
         enable = !isLoading
      )

      Spacer(modifier = Modifier.height(16.dp))

      BasicTextField(
         label = "Пароль",
         placeholder = "Введите пароль",
         value = password,
         onValueChange = { password = it },
         enable = !isLoading,
         isPassword = true

      )

      Spacer(modifier = Modifier.height(100.dp))

      // btm
      BasicButton(
         onClick = {
            if(isFormValid){
               viewModel.login(email, password)
            }
         },
         isFormValid = isFormValid,
         buttonText = if(isLoading) "Вход..." else "Далее"
      )
   }
}

@Preview
@Composable
fun LoginScreenPreview(){
   LoginScreen()
}