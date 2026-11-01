package com.supersuman.andha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.supersuman.andha.ui.theme.AndhaTheme

sealed interface Screen {
    data object Config : Screen
    data object Play : Screen
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndhaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var config by remember { mutableStateOf(BannerConfig()) }
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Config) }

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            if (targetState is Screen.Play) {
                (slideInHorizontally(animationSpec = tween(400)) { width -> width } + fadeIn(animationSpec = tween(400)))
                    .togetherWith(slideOutHorizontally(animationSpec = tween(400)) { width -> -width } + fadeOut(animationSpec = tween(400)))
            } else {
                (slideInHorizontally(animationSpec = tween(400)) { width -> -width } + fadeIn(animationSpec = tween(400)))
                    .togetherWith(slideOutHorizontally(animationSpec = tween(400)) { width -> width } + fadeOut(animationSpec = tween(400)))
            }
        },
        label = "ScreenTransition",
        modifier = Modifier.fillMaxSize()
    ) { screen ->
        when (screen) {
            Screen.Config -> {
                ConfigScreen(
                    config = config,
                    onConfigChange = { config = it },
                    onPlay = { currentScreen = Screen.Play }
                )
            }
            Screen.Play -> {
                PlayScreen(
                    config = config,
                    onBackClicked = { currentScreen = Screen.Config }
                )
            }
        }
    }
}
