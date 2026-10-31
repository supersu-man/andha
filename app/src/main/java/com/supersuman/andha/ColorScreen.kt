package com.supersuman.andha

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.supersuman.andha.ui.theme.AndhaTheme

private val defaultColorSwatches = listOf(
    Color(0xFFE53935), // Red 600
    Color(0xFFD81B60), // Pink 600
    Color(0xFF8E24AA), // Purple 600
    Color(0xFF5E35B1), // Deep Purple 600
    Color(0xFF3949AB), // Indigo 600
    Color(0xFF1E88E5), // Blue 600
    Color(0xFF039BE5), // Light Blue 600
    Color(0xFF00ACC1), // Cyan 600
    Color(0xFF00897B), // Teal 600
    Color(0xFF43A047), // Green 600
    Color(0xFF7CB342), // Light Green 600
    Color(0xFFC0CA33), // Lime 600
    Color(0xFFFDD835), // Yellow 600
    Color(0xFFFFB300), // Amber 600
    Color(0xFFFB8C00), // Orange 600
    Color(0xFFF4511E), // Deep Orange 600
    Color(0xFF6D4C41), // Brown 600
    Color(0xFF757575), // Grey 600
    Color(0xFF546E7A), // Blue Grey 600
    Color(0xFF000000), // Black
    Color(0xFFFFFFFF)  // White
)

@Composable
fun ColorScreen(modifier: Modifier = Modifier) {
    var backgroundColor by remember { mutableStateOf(Color(0xFFE53935)) }
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(30.dp)
        ) {
            Text(text = "Choose Color")
        }
    }

    if (showDialog) {
        ColorPickerDialog(
            onColorSelected = { color ->
                backgroundColor = color
                showDialog = false
            },
            onDismissRequest = { showDialog = false }
        )
    }
}

@Composable
fun ColorPickerDialog(
    onColorSelected: (Color) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Pick Color") },
        text = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(defaultColorSwatches) { color ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape)
                            .background(color)
                            .border(1.dp, Color.Gray, CircleShape)
                            .clickable { onColorSelected(color) }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ColorScreenPreview() {
    AndhaTheme {
        ColorScreen()
    }
}
