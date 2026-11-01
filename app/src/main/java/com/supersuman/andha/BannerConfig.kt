package com.supersuman.andha

import androidx.compose.ui.graphics.Color

enum class ScrollOption(val label: String) {
    NONE("Static"),
    RIGHT_TO_LEFT("Scroll Left"),
    LEFT_TO_RIGHT("Scroll Right"),
    BLINK("Blink")
}

data class BannerConfig(
    val message: String = "Hello World!",
    val backgroundColor: Color = Color(0xFF000000), // Black
    val textColor: Color = Color(0xFFFDD835),       // Yellow
    val scrollOption: ScrollOption = ScrollOption.RIGHT_TO_LEFT,
    val scrollSpeed: Float = 5f,
    val fontSize: Float = 80f
)

val defaultColorSwatches = listOf(
    Color(0xFF000000), // Black
    Color(0xFFFFFFFF), // White
    Color(0xFFE53935), // Red
    Color(0xFFD81B60), // Pink
    Color(0xFF8E24AA), // Purple
    Color(0xFF5E35B1), // Deep Purple
    Color(0xFF1E88E5), // Blue
    Color(0xFF039BE5), // Light Blue
    Color(0xFF00ACC1), // Cyan
    Color(0xFF00897B), // Teal
    Color(0xFF43A047), // Green
    Color(0xFFFDD835), // Yellow
    Color(0xFFFFB300), // Amber
    Color(0xFFFB8C00), // Orange
    Color(0xFFF4511E), // Deep Orange
    Color(0xFF757575)  // Grey
)
