package com.ananttonk.ekacareassignment.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ananttonk.ekacareassignment.ui.ext.NewsAppBar
import com.ananttonk.ekacareassignment.NewsApplication
import com.ananttonk.ekacareassignment.R
import com.ananttonk.ekacareassignment.factory.viewModelFactory
import com.ananttonk.ekacareassignment.model.News
import com.ananttonk.ekacareassignment.viewmodel.NewsViewModel

@Composable
fun HomeScreen(openNewsDetails: (news: News.Source) -> Unit, openSavedArticles: () -> Unit) {
    val viewModel: NewsViewModel =
        viewModel<NewsViewModel>(factory = viewModelFactory {
            NewsViewModel(NewsApplication.instance.getNewsModule().newsRepo)
        })
    LaunchedEffect(Unit) {
        viewModel.getNews()
    }
    val newsList by viewModel.newsList.collectAsState()
    Scaffold(topBar = {
        NewsAppBar(title = "News App", actions = {
            IconButton(
                onClick = {
                    openSavedArticles()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.downloads),
                    contentDescription = "Add to Wishlist",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        })
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color.White)
                .fillMaxSize()
        ) {
            newsList?.let { news ->
                NewsMainContent(news) { article ->
                    openNewsDetails(article)
                }
            }
        }
    }
}

@Composable
fun NewsMainContent(news: News, openNewsDetails: (news: News.Source) -> Unit) {
    LazyColumn {
        items(news.sources) {
            NewsListItem(news = it, openNewsDetails = openNewsDetails)
        }
    }
}

@Composable
fun NewsListItem(
    news: News.Source,
    openNewsDetails: (news: News.Source) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .background(Color(0xFF334442))
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
            ) {
                Text(
                    text = news.name,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.padding(top = 5.dp))
                Text(
                    text = news.description,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(Modifier.padding(top = 14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Language: ${news.language.uppercase()}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .clickable { openNewsDetails(news) },
                        text = "Read More...",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}