package com.ananttonk.ekacareassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ananttonk.ekacareassignment.navigation.NewsNavigation
import com.ananttonk.ekacareassignment.ui.theme.EkaCareAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EkaCareAssignmentTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    Surface {
        val navController = rememberNavController()
        NewsNavigation(navController)
    }
}
