package com.example.kinomobileapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kinomobileapp.R

@Composable
fun MovieCard(
    cardName: String,
    cardRating: String,

){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){

        Image(
            painter = painterResource(R.drawable.film_img),
            contentScale = ContentScale.Fit,
            contentDescription = "film img",пе
            modifier = Modifier.height(50.dp)
        )

        Spacer(modifier = Modifier.width(60.dp))

        Text(
            text = cardName,
            color = Color(0xFF17418C),
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.width(60.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            Image(
                painter = painterResource(R.drawable.star),
                contentScale = ContentScale.Fit,
                contentDescription = "star",
                modifier = Modifier.height(120.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = cardRating,
                color = Color(0xFF17418C),
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }


    }
}

@Preview
@Composable
fun MovieCardPreview(){
    MovieCard(
        cardRating = "8,7",
        cardName = "Интерстеллар"
    )
}