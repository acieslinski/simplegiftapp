package com.amc.acieslinski.simplegiftapp.android.feature.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.DashboardViewModel
import org.koin.androidx.compose.getViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.DashboardAlertState
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.DrawingsUiState
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.DrawingUiState
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.FakeDashboardViewModel
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.getFormattedDate

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = getViewModel(),
    onDrawingAddClick: () -> Unit = {},
    onDrawingClick: (drawingId: String) -> Unit = {},
) {
    val dashboardState by viewModel.dashboardUiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = "Welcome X,X",
                modifier = Modifier
                    .padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            Button(
                onClick = { viewModel.onShowUserPublicIdQrCodeAction() },
                modifier = Modifier
                    .padding(horizontal = 16.dp) // Button takes full width with horizontal padding
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Participate in a draw",
                    style = MaterialTheme.typography.bodyLarge, // Ensure the text style fits
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Your Drawings:",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            when (val state = dashboardState.drawingsState) {
                is DrawingsUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(state.drawings) { drawingUiState ->
                            DrawingItem(drawingUiState = drawingUiState) {
                                viewModel.onSelectDrawingAction(it.id)
                                onDrawingClick(it.id)
                            }
                        }
                    }
                }

                is DrawingsUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }



                else -> {
                    Text(
                        text = "No drawings available.",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        UserPublicIdQrCodeDialog(dialogState = dashboardState.userPublicIdQrCodeUiState) {
            viewModel.onHideUserPublicIdQrCodeAction()
        }

        DashboardDialog(alertState = dashboardState.alertState) {
            viewModel.onAlertAckAction()
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

@Composable
fun DrawingItem(drawingUiState: DrawingUiState, onClick: (DrawingUiState) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(drawingUiState) }
    ) {
        Text(
            text = "${drawingUiState.orderNumber}.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(0.2f) // Fixed width for order number
        )
        Column(modifier = Modifier.weight(0.8f)) {
            val formattedDate = drawingUiState.getFormattedDate()
            Text(
                text = drawingUiState.title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SampleScreenPreview() {
    MaterialTheme {
        DashboardScreen(
            viewModel = FakeDashboardViewModel()
        )
    }
}