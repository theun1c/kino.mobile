package com.example.kinomobileapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kinomobileapp.R
import com.example.kinomobileapp.domain.models.Movie

@Composable
fun MovieCard(
    movie: Movie,
    isDeleting: Boolean = false,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier

){
    Row(
        modifier = Modifier.fillMaxWidth()
            .alpha(if (isDeleting) 0.5f else 1f),
        verticalAlignment = Alignment.CenterVertically,
    ){
        // IMAGE
        Image(
            painter = painterResource(R.drawable.film_img),
            contentScale = ContentScale.Fit,
            contentDescription = "film img",
            modifier = Modifier
                .height(80.dp)
                .weight(1f)
        )

        Text(
            text = movie.title,
            color = Color(0xFF17418C),
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Center
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ){

            Image(
                painter = painterResource(R.drawable.star),
                contentScale = ContentScale.Fit,
                contentDescription = "star",
                modifier = Modifier.height(120.dp),

            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = movie.ratingIMDB.toString(),
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
    val movie = Movie(
        id = "id_123",
        title = "asdasd",
        description = "descr",
        ratingKinoPoisk = 10.0,
        genre = "genre1",
        country = "country 1",
        director = "directo1",
        ratingIMDB = 9.1,
    )

    MovieCard(
        movie = movie,
    )
}