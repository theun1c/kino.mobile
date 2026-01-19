package com.example.kinomobileapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kinomobileapp.R
import com.example.kinomobileapp.ui.components.BasicButton
import com.example.kinomobileapp.ui.viewmodels.AddMovieViewModel
import com.example.kinomobileapp.ui.viewmodels.UpdateMovieViewModel
import kotlinx.coroutines.delay

@Composable
fun UpdateScreen(
    navController: NavController = rememberNavController(),
    movieId: String,  // ID фильма для редактирования
    viewModel: UpdateMovieViewModel = hiltViewModel(),
    onMovieUpdated: () -> Unit = {}
) {
    // Загружаем фильм при открытии экрана
    LaunchedEffect(movieId) {
        if (movieId.isNotEmpty()) {
            viewModel.loadMovie(movieId)
        }
    }

    val movie by viewModel.movie.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val updateSuccess by viewModel.updateSuccess.collectAsState()

    // Состояния для полей формы
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var kinopoiskRating by remember { mutableStateOf("") }
    var imdbRating by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var countries by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }

    // Заполняем поля когда загрузился фильм
    LaunchedEffect(movie) {
        movie?.let {
            title = it.title
            description = it.description
            kinopoiskRating = it.ratingKinoPoisk.toString()
            imdbRating = it.ratingIMDB.toString()
            genre = it.genre
            countries = it.country
            director = it.director
        }
    }

    // Обработка успешного обновления
    LaunchedEffect(updateSuccess) {
        if (updateSuccess) {
            onMovieUpdated()
            delay(300)
            navController.popBackStack()
            viewModel.resetState()
        }
    }

    // Проверка валидности формы
    val isFormValid = title.isNotEmpty() &&
            description.isNotEmpty() &&
            kinopoiskRating.isNotEmpty() &&
            imdbRating.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 32.dp),
    ) {

        if (isLoading && movie == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Форма
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Название фильма
                Column {
                    BasicTextField(
                        value = title,
                        onValueChange = { title = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFF4CBBBF),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 12.dp),
                        textStyle = TextStyle(
                            color = Color(0xFF17418C),
                            fontSize = 15.sp
                        ),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ){
                                if (title.isEmpty()) {
                                    Text(
                                        text = "Введите название фильма",
                                        color = Color.Gray,
                                        fontSize = 15.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }

                // Изображение
                Image(
                    painter = painterResource(R.drawable.film_img),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Постер фильма",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                // Описание
                Column {
                    Text(
                        text = "Описание:",
                        color = Color(0xFF17418C),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BasicTextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFF4CBBBF),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        textStyle = TextStyle(
                            color = Color(0xFF17418C),
                            fontSize = 14.sp
                        )
                    )
                }

                // Рейтинги
                Row() {
                    // КиноПоиск
                    Column(
                    ) {
                        Text(
                            text = "КиноПоиск:",
                            color = Color(0xFF17418C),
                            fontSize = 14.sp
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BasicTextField(
                                value = kinopoiskRating,
                                onValueChange = { newValue ->
                                    if (newValue.matches(Regex("^\\d*(\\.\\d*)?$")) && newValue.length <= 4) {
                                        kinopoiskRating = newValue
                                    }
                                },
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(20.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFF4CBBBF),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(horizontal = 4.dp),
                                textStyle = TextStyle(
                                    color = Color(0xFF17418C),
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                ),
                                singleLine = true,
                                decorationBox = { innerTextField ->
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.CenterStart  // ВЕРТИКАЛЬНО ПО ЦЕНТРУ
                                    ) {
                                        innerTextField()
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("из 10", fontSize = 12.sp, color = Color(0xFF17418C))
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    // IMDb
                    Column(
                    ) {
                        Text(
                            text = "IMDb:",
                            color = Color(0xFF17418C),
                            fontSize = 14.sp
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BasicTextField(
                                value = imdbRating,
                                onValueChange = { newValue ->
                                    if (newValue.matches(Regex("^\\d*(\\.\\d*)?$")) && newValue.length <= 4) {
                                        imdbRating = newValue
                                    }
                                },
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(20.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFF4CBBBF),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(horizontal = 4.dp),
                                textStyle = TextStyle(
                                    color = Color(0xFF17418C),
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                ),
                                singleLine = true,
                                decorationBox = { innerTextField ->
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.CenterStart  // ВЕРТИКАЛЬНО ПО ЦЕНТРУ
                                    ) {
                                        innerTextField()
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("из 10", fontSize = 12.sp, color = Color(0xFF17418C))
                        }
                    }
                }

                // Жанр
                Row {
                    Text(
                        text = "Жанр:",
                        color = Color(0xFF17418C),
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(2.dp))

                    BasicTextField(
                        value = genre,
                        onValueChange = { genre = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFF4CBBBF),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp),
                        textStyle = TextStyle(
                            color = Color(0xFF17418C),
                            fontSize = 14.sp,
                        ),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            Box(
                                contentAlignment = Alignment.CenterStart
                            ) {
                                innerTextField()
                            }
                        }
                    )
                }

                // Страна
                Row {
                    Text(
                        text = "Страна:",
                        color = Color(0xFF17418C),
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(2.dp))

                    BasicTextField(
                        value = countries,
                        onValueChange = { countries = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFF4CBBBF),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp),
                        textStyle = TextStyle(
                            color = Color(0xFF17418C),
                            fontSize = 14.sp,
                        ),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            Box(
                                contentAlignment = Alignment.CenterStart
                            ) {
                                innerTextField()
                            }
                        }
                    )
                }

                // Режиссер
                Row {
                    Text(
                        text = "Режиссер:",
                        color = Color(0xFF17418C),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    BasicTextField(
                        value = director,
                        onValueChange = { director = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFF4CBBBF),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp),
                        textStyle = TextStyle(
                            color = Color(0xFF17418C),
                            fontSize = 14.sp,
                        ),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            Box(
                                contentAlignment = Alignment.CenterStart
                            ) {
                                innerTextField()
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Кнопка обновления
                BasicButton(
                    buttonText = if (isLoading) "Обновление..." else "Обновить",
                    isFormValid = isFormValid && !isLoading,
                    onClick = {
                        val kpRating = kinopoiskRating.toDoubleOrNull() ?: 0.0
                        val imdbRatingValue = imdbRating.toDoubleOrNull() ?: 0.0

                        viewModel.updateMovie(
                            movieId = movieId,
                            title = title,
                            description = description,
                            ratingKinoPoisk = kpRating,
                            ratingIMDB = imdbRatingValue,
                            genre = genre,
                            country = countries,
                            director = director
                        )
                    },
                    isLoading = isLoading
                )
            }
        }
    }
}