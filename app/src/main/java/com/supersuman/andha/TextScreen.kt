package com.supersuman.andha

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.supersuman.andha.ui.theme.AndhaTheme

@Composable
fun TextScreen(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var fontSize by remember { mutableFloatStateOf(100f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    text = "Type",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = fontSize.sp)
                )
            },
            textStyle = TextStyle(
                fontSize = fontSize.sp,
                textAlign = TextAlign.Center
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        )

        Slider(
            value = fontSize,
            onValueChange = { fontSize = it },
            valueRange = 50f..150f,
            steps = 19,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextScreenPreview() {
    AndhaTheme {
        TextScreen()
    }
}
