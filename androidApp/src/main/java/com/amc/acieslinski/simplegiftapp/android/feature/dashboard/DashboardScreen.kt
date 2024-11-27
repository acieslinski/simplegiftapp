package com.amc.acieslinski.simplegiftapp.android.feature.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    onDrawingAddClick: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = "Welcome X,X",
                modifier = Modifier
                    .padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(horizontal = 16.dp) // Button takes full width with horizontal padding
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Participate in a draw",
                    style = MaterialTheme.typography.bodyLarge, // Ensure the text style fits
                )
            }
        }

        FloatingActionButton(
            onClick = { onDrawingAddClick() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SampleScreenPreview() {
    MaterialTheme { // Wrap in MaterialTheme to use Material3 styles
        WelcomeScreen()
    }
}