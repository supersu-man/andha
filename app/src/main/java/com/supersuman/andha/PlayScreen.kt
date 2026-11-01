package com.supersuman.andha

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.supersuman.andha.ui.theme.AndhaTheme
import kotlinx.coroutines.isActive

@Composable
fun PlayScreen(
    config: BannerConfig,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Intercept back button gesture
    BackHandler(onBack = onBackClicked)

    var showOverlayControls by remember { mutableStateOf(value = true) }

    // Auto-hide top overlay back button after 3 seconds
    LaunchedEffect(showOverlayControls) {
        if (showOverlayControls) {
            kotlinx.coroutines.delay(3000)
            showOverlayControls = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(config.backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                showOverlayControls = !showOverlayControls
            }
    ) {
        // Banner Text Content
        BannerContent(config = config)

        // Overlay Close/Back Button
        AnimatedVisibility(
            visible = showOverlayControls,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            IconButton(
                onClick = onBackClicked,
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Exit Play Mode",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun BannerContent(
    config: BannerConfig,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val boxScope = this
        val density = LocalDensity.current
        val screenWidthPx = remember(boxScope.maxWidth, density) { with(density) { boxScope.maxWidth.toPx() } }
        var textWidthPx by remember { mutableFloatStateOf(0f) }

        val scrollAnim = remember { Animatable(0f) }

        LaunchedEffect(textWidthPx, screenWidthPx, config.scrollOption, config.scrollSpeed) {
            if (textWidthPx <= 0f) return@LaunchedEffect
            if ((config.scrollOption == ScrollOption.NONE) || (config.scrollOption == ScrollOption.BLINK)) return@LaunchedEffect

            val totalDistance = screenWidthPx + textWidthPx
            val speedFactor = config.scrollSpeed.coerceAtLeast(1f)
            val durationMs = ((totalDistance / (speedFactor * 120f)) * 1000).toInt().coerceAtLeast(300)

            while (isActive) {
                when (config.scrollOption) {
                    ScrollOption.RIGHT_TO_LEFT -> {
                        scrollAnim.snapTo(screenWidthPx)
                        scrollAnim.animateTo(
                            targetValue = -textWidthPx,
                            animationSpec = tween(durationMillis = durationMs, easing = LinearEasing)
                        )
                    }
                    ScrollOption.LEFT_TO_RIGHT -> {
                        scrollAnim.snapTo(-textWidthPx)
                        scrollAnim.animateTo(
                            targetValue = screenWidthPx,
                            animationSpec = tween(durationMillis = durationMs, easing = LinearEasing)
                        )
                    }
                    else -> {}
                }
            }
        }

        val blinkAlpha = if (config.scrollOption == ScrollOption.BLINK) {
            val infiniteTransition = rememberInfiniteTransition(label = "BlinkTransition")
            val blinkDurationMs = (1000 / config.scrollSpeed.coerceAtLeast(1f)).toInt().coerceIn(100, 2000)
            val alpha by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = blinkDurationMs, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "BlinkAlpha"
            )
            alpha
        } else 1f

        val displayText = config.message.ifBlank { "Hello World!" }

        if ((config.scrollOption == ScrollOption.RIGHT_TO_LEFT) || (config.scrollOption == ScrollOption.LEFT_TO_RIGHT)) {
            val marqueeText = displayText.replace("\n", " ")
            Text(
                text = marqueeText,
                color = config.textColor,
                fontSize = config.fontSize.sp,
                lineHeight = (config.fontSize * 1.2f).sp,
                fontWeight = FontWeight.Bold,
                softWrap = false,
                maxLines = 1,
                modifier = Modifier
                    .wrapContentSize(unbounded = true)
                    .onGloballyPositioned { coordinates ->
                        textWidthPx = coordinates.size.width.toFloat()
                    }
                    .graphicsLayer {
                        translationX = scrollAnim.value
                    }
            )
        } else {
            Text(
                text = displayText,
                color = config.textColor.copy(alpha = blinkAlpha),
                fontSize = config.fontSize.sp,
                lineHeight = (config.fontSize * 1.2f).sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .onGloballyPositioned { coordinates ->
                        textWidthPx = coordinates.size.width.toFloat()
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayScreenPreview() {
    AndhaTheme {
        PlayScreen(
            config = BannerConfig(
                message = "Hello World!",
                scrollOption = ScrollOption.RIGHT_TO_LEFT
            ),
            onBackClicked = {}
        )
    }
}
