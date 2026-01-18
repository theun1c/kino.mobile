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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.kinomobileapp.R
import com.example.kinomobileapp.ui.components.BasicButton

@Composable
fun AddScreen(
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var kinopoiskRating by remember { mutableStateOf("") }
    var imdbRating by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var countries by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicTextField(
                value = title,
                onValueChange = {title = it},
                modifier = Modifier
                    .height(36.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF4CBBBF),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(4.dp),
                textStyle = TextStyle(
                    color = Color(0xFF17418C),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if(title.isEmpty()){
                            Text(
                                text = "Название фильма",
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(R.drawable.film_img),
                contentScale = ContentScale.Fit,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Описание:",
            color = Color(0xFF17418C),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(2.dp))

        BasicTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .height(220.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFF4CBBBF),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(2.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                color = Color(0xFF17418C),
                fontSize = 12.sp
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(4.dp),
                ) {
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(){

            Column {
                Text(
                    text = "Кинопоиск:",
                    color = Color(0xFF17418C),
                    fontSize = 12.sp
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    BasicTextField(
                        value =kinopoiskRating,
                        onValueChange = { kinopoiskRating = it },
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color(0xFF4CBBBF),
                                shape = RoundedCornerShape(7.dp)
                            )
                            .width(28.dp),
                        textStyle = TextStyle(
                            color = Color(0xFF17418C),
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .padding(4.dp),
                            ) {
                                innerTextField()
                            }
                        }
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "из 10",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF17418C)
                    )
                }

            }

            Spacer(modifier = Modifier.width(15.dp))

            Column {
                Text(
                    text = "IMDb:",
                    color = Color(0xFF17418C),
                    fontSize = 12.sp
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    BasicTextField(
                        value = imdbRating,
                        onValueChange = { imdbRating = it },
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color(0xFF4CBBBF),
                                shape = RoundedCornerShape(7.dp)
                            )
                            .width(28.dp),
                        textStyle = TextStyle(
                            color = Color(0xFF17418C),
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .padding(4.dp),
                            ) {
                                innerTextField()
                            }
                        }
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "из 10",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF17418C)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Жанр: ",
                fontSize = 12.sp,
                color = Color(0xFF17418C)
            )

            BasicTextField(
                value = genre,
                onValueChange = {genre = it},
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF4CBBBF),
                        shape = RoundedCornerShape(7.dp)
                    ),
                textStyle = TextStyle(
                    color = Color(0xFF17418C),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp),
                    ) {
                        innerTextField()
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Страны: ",
                fontSize = 12.sp,
                color = Color(0xFF17418C)
            )

            BasicTextField(
                value = countries,
                onValueChange = { countries = it },
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF4CBBBF),
                        shape = RoundedCornerShape(7.dp)
                    ),
                textStyle = TextStyle(
                    color = Color(0xFF17418C),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp),
                    ) {
                        innerTextField()
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Режиссер: ",
                fontSize = 12.sp,
                color = Color(0xFF17418C)
            )

            BasicTextField(
                value = director,
                onValueChange = { director = it },
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF4CBBBF),
                        shape = RoundedCornerShape(7.dp)
                    ),
                textStyle = TextStyle(
                    color = Color(0xFF17418C),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp),
                    ) {
                        innerTextField()
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        BasicButton(
            buttonText = "Добавить",
            isFormValid = true,
            onClick = {},
            isLoading = false
        )
    }
}

@Preview
@Composable
fun AddScreenPreview(){
    AddScreen(
    )
}