package com.example.kinomobileapp.ui.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BasicTextField(
    label: String,
    placeholder: String
){
    var text by remember { mutableStateOf("") }

    Column {
        Text(
            text = label,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 6.dp),
            color = Color.Gray
        )

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = placeholder) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFF17418C),
                unfocusedTextColor = Color.Gray,
                focusedBorderColor = Color(0xFF4CBBBF),
                unfocusedBorderColor = Color(0xFF4CBBBF),
            ),
            shape = RoundedCornerShape(18.dp)
        )
    }
}

@Preview
@Composable
fun BasicTextFieldPreview(){
    BasicTextField(
        label = "Логин",
        placeholder = "Введите почту"
    )
}