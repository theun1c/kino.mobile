package com.example.kinomobileapp.ui.screens

import android.media.Rating
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kinomobileapp.ui.components.MovieCard
import com.example.kinomobileapp.R


data class MovieData(
    val rating: String,
    val name: String
)

@Composable
fun MovieCatalogScreen(){

    val movieList = listOf(
        MovieData("8.1", "Интерстеллар"),
        MovieData("3.1", "Интерстеллар55"),
        MovieData("4.1", "Интерстеллар3"),
        MovieData("1.1", "Интерстеллар2"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        )
    {
        Text(
            text = "Каталог фильмов:",
            color = Color(0xFF17418C)
        )

        // lazy column with film-card components

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(movieList) { movie ->
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ) {
                    Image(
                        painter = painterResource(R.drawable.trash_img),

                        contentDescription = "trash img",
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    MovieCard(
                        cardRating = movie.rating,
                        cardName = movie.name
                    )
                }
                // Разделитель после каждого элемента, кроме последнего
                if (movie != movieList.last()) {
                    Divider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        color = Color(0xFF4CBBBF),
                        thickness = 1.dp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieCatalogScreenPreview(){
    MovieCatalogScreen()
}