package com.ananttonk.ekacareassignment.ui.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    backPressCallBack: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                title, color = Color.White, maxLines = 1, fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        },
        actions = actions,
        navigationIcon = {
            if (backPressCallBack != null)
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White, modifier = Modifier.clickable {
                            backPressCallBack.invoke()
                        }
                    )
                }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF3F51B5),
        )
    )
}