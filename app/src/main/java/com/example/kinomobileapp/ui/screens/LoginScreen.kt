package com.example.kinomobileapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kinomobileapp.ui.components.BasicTextField

@Composable
fun LoginScreen(){

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
         placeholder = "Введите почту"
      )

      Spacer(modifier = Modifier.height(16.dp))

      BasicTextField(
         label = "Пароль",
         placeholder = "Введите пароль"
      )

      // btm
   }


}

@Preview
@Composable
fun LoginScreenPreview(){
   LoginScreen()
}