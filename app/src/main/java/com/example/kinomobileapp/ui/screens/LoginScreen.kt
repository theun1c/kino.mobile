package com.example.kinomobileapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kinomobileapp.ui.components.BasicButton
import com.example.kinomobileapp.ui.components.BasicTextField

@Composable
fun LoginScreen(){

   var email by remember { mutableStateOf("") }
   var password by remember { mutableStateOf("") }

   var isFormValid = email.isNotEmpty() && password.isNotEmpty()

   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(32.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,

   ) {
      // icon


      // 2 TF
      BasicTextField(
         label = "Логин",
         placeholder = "Введите почту",
         value = email,
         onValueChange = { email = it }
      )

      Spacer(modifier = Modifier.height(16.dp))

      BasicTextField(
         label = "Пароль",
         placeholder = "Введите пароль",
         value = password,
         onValueChange = { password = it }
      )

      Spacer(modifier = Modifier.height(100.dp))

      // btm
      BasicButton(
         onClick = {},
         isFormValid = isFormValid,
         buttonText = "Далее"
      )
   }
}

@Preview
@Composable
fun LoginScreenPreview(){
   LoginScreen()
}