package com.example.kinomobileapp.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.ktor.websocket.Frame

@Composable
fun BasicButton(
    buttonText: String,
    isFormValid: Boolean,
    onClick: () -> Unit,
    isLoading: Boolean = false
){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(isFormValid) Color(0xFF17418C) else Color(0xFF899FC6),
            contentColor = Color.White,
            disabledContentColor = Color.LightGray,
            disabledContainerColor = Color(0xFF899FC6),
        ),
        enabled = isFormValid && !isLoading,

    ) {
        Text(text = buttonText)
    }
}

@Preview
@Composable
fun BasicButtonPreview(){
    BasicButton(
        buttonText = "Далее",
        isFormValid = false,
        onClick = {}
    )
}