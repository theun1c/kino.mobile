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
import androidx.compose.material3.IconButton
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

@Composable
fun AddScreen(
    navController: NavController = rememberNavController(),
    viewModel: AddMovieViewModel = hiltViewModel(),
    onMovieAdded: () -> Unit = {}
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var kinopoiskRating by remember { mutableStateOf("") }
    var imdbRating by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var countries by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }

    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val addSuccess by viewModel.addSuccess.collectAsState()

    // Обработка успешного добавления
    LaunchedEffect(addSuccess) {
        if (addSuccess) {
            onMovieAdded()
            // Возвращаемся назад после небольшой задержки
            kotlinx.coroutines.delay(500)
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
            .padding(horizontal = 32.dp, vertical = 16.dp),
    ) {
//        // Заголовок с кнопкой назад
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
////            IconButton(
////                onClick = { navController.popBackStack() }
////            ) {
////                Image(
////                    painter = painterResource(R.drawable.arrow_back),
////                    contentDescription = "Назад",
////                    modifier = Modifier.size(24.dp)
////                )
////            }
//
//            Text(
//                text = "Добавить фильм",
//                color = Color(0xFF17418C),
//                fontSize = 18.sp,
//                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
//            )
//
//            Spacer(modifier = Modifier.width(48.dp))
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Сообщение об ошибке
//        if (error != null) {
//            Text(
//                text = error!!,
//                color = MaterialTheme.colorScheme.error,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp)
//            )
//        }
//
//        // Сообщение об успехе
//        if (addSuccess) {
//            Text(
//                text = "Фильм успешно добавлен!",
//                color = Color.Green,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp)
//            )
//        }

        // Форма
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Название фильма
            Column {
                Text(
                    text = "Название фильма:",
                    color = Color(0xFF17418C),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                BasicTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF4CBBBF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp),
                    textStyle = TextStyle(
                        color = Color(0xFF17418C),
                        fontSize = 16.sp
                    ),
                    singleLine = true
                )
            }

            // Изображение
            Image(
                painter = painterResource(R.drawable.film_img),
                contentScale = ContentScale.Fit,
                contentDescription = "Постер фильма",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
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
                        .height(120.dp)
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // КиноПоиск
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "КиноПоиск:",
                        color = Color(0xFF17418C),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
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
                                .width(60.dp)
                                .height(40.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF4CBBBF),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 8.dp),
                            textStyle = TextStyle(
                                color = Color(0xFF17418C),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            ),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("из 10", fontSize = 12.sp, color = Color.Gray)
                    }
                }

                // IMDb
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "IMDb:",
                        color = Color(0xFF17418C),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
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
                                .width(60.dp)
                                .height(40.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF4CBBBF),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 8.dp),
                            textStyle = TextStyle(
                                color = Color(0xFF17418C),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            ),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("из 10", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }

            // Жанр
            Column {
                Text(
                    text = "Жанр:",
                    color = Color(0xFF17418C),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                BasicTextField(
                    value = genre,
                    onValueChange = { genre = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF4CBBBF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp),
                    textStyle = TextStyle(
                        color = Color(0xFF17418C),
                        fontSize = 14.sp
                    ),
                    singleLine = true
                )
            }

            // Страна
            Column {
                Text(
                    text = "Страна:",
                    color = Color(0xFF17418C),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                BasicTextField(
                    value = countries,
                    onValueChange = { countries = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF4CBBBF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp),
                    textStyle = TextStyle(
                        color = Color(0xFF17418C),
                        fontSize = 14.sp
                    ),
                    singleLine = true
                )
            }

            // Режиссер
            Column {
                Text(
                    text = "Режиссер:",
                    color = Color(0xFF17418C),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                BasicTextField(
                    value = director,
                    onValueChange = { director = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF4CBBBF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp),
                    textStyle = TextStyle(
                        color = Color(0xFF17418C),
                        fontSize = 14.sp
                    ),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Кнопка добавления
            BasicButton(
                buttonText = if (isLoading) "Добавление..." else "Добавить фильм",
                isFormValid = isFormValid && !isLoading,
                onClick = {
                    val kpRating = kinopoiskRating.toDoubleOrNull() ?: 0.0
                    val imdbRatingValue = imdbRating.toDoubleOrNull() ?: 0.0

                    viewModel.addMovie(
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

@Preview
@Composable
fun AddScreenPreview(){
    AddScreen(
    )
}