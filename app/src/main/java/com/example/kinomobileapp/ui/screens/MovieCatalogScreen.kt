package com.example.kinomobileapp.ui.screens

import android.media.Rating
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kinomobileapp.ui.components.MovieCard
import com.example.kinomobileapp.R
import com.example.kinomobileapp.ui.viewmodels.MovieViewModel


data class MovieData(
    val rating: String,
    val name: String
)

@Composable
fun MovieCatalogScreen(
    viewModel: MovieViewModel = hiltViewModel()
){
    val movies by viewModel.movies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

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

        Spacer(modifier = Modifier.height(16.dp))

        if(isLoading){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (error != null){
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = error ?: "Error",
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.loadMovies() }
                ) {
                    Text(text = "Retry")
                }
            }
        } else if (movies.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Фильмы не найдены")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(movies) { movie ->
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
                            movie = movie,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    // Разделитель после каждого элемента, кроме последнего
                    if (movie != movies.last()) {
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
}

@Preview
@Composable
fun MovieCatalogScreenPreview(){
    MovieCatalogScreen()
}