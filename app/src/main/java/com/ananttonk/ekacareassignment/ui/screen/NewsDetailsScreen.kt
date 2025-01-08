package com.ananttonk.ekacareassignment.ui.screen

import android.content.Intent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ananttonk.ekacareassignment.ui.ext.NewsAppBar
import com.ananttonk.ekacareassignment.NewsApplication
import com.ananttonk.ekacareassignment.R
import com.ananttonk.ekacareassignment.factory.viewModelFactory
import com.ananttonk.ekacareassignment.model.News
import com.ananttonk.ekacareassignment.viewmodel.NewsViewModel

@Composable
fun NewsDetails(
    news: News.Source?,
    onBack: () -> Unit
) {
    val viewModel: NewsViewModel =
        viewModel<NewsViewModel>(factory = viewModelFactory {
            NewsViewModel(NewsApplication.instance.getNewsModule().newsRepo)
        })
    LaunchedEffect(Unit) {
        viewModel.getArticleById(news?.id.orEmpty())
    }
    val article by viewModel.articleById.collectAsState()
    val context = LocalContext.current
    Scaffold(topBar = {
        NewsAppBar(title = "News Details", backPressCallBack = { onBack() }, actions = {
            if (article == null) {
                IconButton(onClick = {
                    if (news != null) {
                        viewModel.insertNewsArticle(news)
                        viewModel.getArticleById(news.id)
                    } else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.downlaod_news),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            IconButton(onClick = {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, news?.url)
                    type = "text/plain"
                }
                context.startActivity(Intent.createChooser(shareIntent, "Share Article"))
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = null,
                    tint = Color.White
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
            if (news != null) {
                NewsDetailsContent(news = news)
            } else {
                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    text = "No Saved Articles",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
fun NewsDetailsContent(news: News.Source) {
    WebViewWithLoading(url = news.url)
}

@Composable
fun WebViewWithLoading(url: String) {
    var isLoading by remember { mutableStateOf(true) }
    var progress by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    webChromeClient = object : WebChromeClient() {
                        override fun onProgressChanged(view: WebView?, newProgress: Int) {
                            progress = newProgress
                            isLoading = newProgress < 100
                        }
                    }
                    loadUrl(url)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = Color.Blue)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Loading $progress%", color = Color.Black)
            }
        }
    }
}

