package com.compose.newsapplite.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.newsapplite.presentation.news.NavGraphs
import com.compose.newsapplite.presentation.news.NewsViewModel
import com.compose.newsapplite.ui.theme.NewsAppLiteTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppLiteTheme {
                AppNavigation(this@MainActivity)
            }
        }
    }
}

@Composable
private fun AppNavigation(activity: ComponentActivity) {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        dependenciesContainerBuilder = {
            dependency(hiltViewModel<NewsViewModel>(activity))
        }
    )
}