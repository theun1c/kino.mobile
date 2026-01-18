package com.example.kinomobileapp.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BasicTextField(
    label: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    value: String,
    isPassword: Boolean = false,
    enabled: Boolean = true
) {

    Column {
        Text(
            text = label,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 6.dp),
            color = Color.Gray
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = placeholder) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFF17418C),
                unfocusedTextColor = Color.Gray,
                focusedBorderColor = Color(0xFF4CBBBF),
                unfocusedBorderColor = Color(0xFF4CBBBF),
            ),
            shape = RoundedCornerShape(18.dp),
            visualTransformation = if(isPassword){
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
        )
    }
}

@Preview
@Composable
fun BasicTextFieldPreview(){

    var login by remember { mutableStateOf("") }
    BasicTextField(
        label = "Логин",
        placeholder = "Введите почту",
        value = login,
        onValueChange = { login = it }
    )
}