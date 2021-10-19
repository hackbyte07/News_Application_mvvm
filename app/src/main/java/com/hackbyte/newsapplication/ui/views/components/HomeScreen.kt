package com.hackbyte.newsapplication.ui.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.hackbyte.newsapplication.R
import com.hackbyte.newsapplication.domain.NewsApiState
import com.hackbyte.newsapplication.ui.MainViewModel
import com.hackbyte.newsapplication.ui.views.navigation.Navigation
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    var query by remember {
        mutableStateOf("")
    }
    var showSearchedNews by remember {
        mutableStateOf(false)
    }

    val topHeadlines = mainViewModel.topHeadlines.value
    val searchNews = mainViewModel.searchNews.value

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 12.dp,
                backgroundColor = MaterialTheme.colors.background
            ) {
                TextField(
                    value = query,
                    onValueChange = {
                        query = it
                    },
                    label = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            showSearchedNews = mainViewModel.searchNews(query)
                            query = ""
                        }
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }

        }
    ) {
        if (showSearchedNews) {

            when (searchNews) {
                NewsApiState(isLoading = searchNews.isLoading) -> {
                    CircularIndicator(show = searchNews.isLoading)
                }

                NewsApiState(data = searchNews.data) -> {
                    LazyColumn {
                        val items = searchNews.data.articles
                        items(items) {
                            NewsCard(
                                imageUrl = it.urlToImage ?: "",
                                title = it.title,
                                description = it.description ?: ""
                            ) {
                                val encodedUrl =
                                    URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
                                navController.navigate(Navigation.DetailScreen.route.plus("/${encodedUrl}")) {
                                    popUpTo(Navigation.HomeScreen.route)
                                    launchSingleTop = true
                                }
                            }
                        }
                    }
                }

                NewsApiState(error = searchNews.error) -> {
                    ShowError(error = searchNews.error)
                }
            }

        } else {

            when (topHeadlines) {
                NewsApiState(isLoading = topHeadlines.isLoading) -> {
                    CircularIndicator(show = searchNews.isLoading)
                }

                NewsApiState(data = topHeadlines.data) -> {
                    LazyColumn {
                        val items = topHeadlines.data.articles
                        items(items) {
                            NewsCard(
                                imageUrl = it.urlToImage ?: "",
                                title = it.title,
                                description = it.description ?: ""
                            ) {
                                val encodedUrl =
                                    URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
                                navController.navigate(Navigation.DetailScreen.route.plus("/${encodedUrl}")) {
                                    popUpTo(Navigation.HomeScreen.route)
                                    launchSingleTop = true
                                }
                            }
                        }
                    }
                }

                NewsApiState(error = topHeadlines.error) -> {
                    ShowError(error = topHeadlines.error)
                }
            }

        }
    }


}


//news card
@ExperimentalMaterialApi
@Composable
fun NewsCard(
    imageUrl: String,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.5.dp, bottom = 7.5.dp, start = 15.dp, end = 15.dp),
        elevation = 8.dp,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        placeholder(R.drawable.ic_baseline_image_24)
                        error(R.drawable.ic_baseline_image_24)
                    }
                ),
                contentDescription = "news Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp)
            )

            Text(
                text = title,
                fontSize = 16.sp,
                maxLines = 2,
                modifier = Modifier
                    .padding(top = 7.5.dp, bottom = 7.5.dp, start = 15.dp, end = 15.dp)
            )

            Text(
                text = description,
                fontSize = 14.sp,
                maxLines = 2,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(bottom = 7.5.dp, start = 15.dp, end = 15.dp)
            )
        }
    }
}

//circular indicator bar
@Composable
fun CircularIndicator(show: Boolean) {
    if (show) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}

//show error
@Composable
fun ShowError(error: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = error,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(
                    top = 12.5.dp, bottom = 12.5.dp, start = 25.dp, end = 25.dp
                )
        )

    }
}