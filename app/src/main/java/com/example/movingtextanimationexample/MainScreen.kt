package com.example.movingtextanimationexample

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalAnimationApi
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {
    val seconds by mainViewModel.seconds.collectAsState(initial = "00")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedContent(
            targetState = seconds,
            transitionSpec = {
                             addAnimation().using(
                                 SizeTransform(clip = true)
                             )
            },
        ) { targetCount ->
            Text(
                text = "$targetCount",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.h1.fontSize,
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 800): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) {height -> height} + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> -height} + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}