package com.amc.acieslinski.simplegiftapp.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amc.acieslinski.simplegiftapp.android.feature.registration.RegistrationScreen
import com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement.DrawingScreen
import com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement.NewDrawingScreen
import com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement.ParticipantQrScannerScreen
import com.amc.acieslinski.simplegiftapp.android.feature.dashboard.DashboardScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.REGISTRATION.route,
        modifier = modifier,
    ) {
        composable(Screens.REGISTRATION.route) {
            RegistrationScreen {
                navController.navigate(Screens.DASHBOARD.route) {
                    popUpTo(Screens.REGISTRATION.route) { inclusive = true }
                }
            }
        }
        composable(Screens.DRAWING_PARTICIPANT_QR_SCANNER.route) {
            ParticipantQrScannerScreen(
                viewModel = getViewModel(),
                onDismiss = navController::navigateUp
            )
        }
        composable(Screens.DRAWING.route) {
            DrawingScreen(
                viewModel = getViewModel(),
                onAddParticipantClicked = {
                    navController.navigate(Screens.DRAWING_PARTICIPANT_QR_SCANNER.route)
                }
            )
        }
        composable(Screens.DASHBOARD.route) {
            DashboardScreen(
                onDrawingAddClick = { navController.navigate(Screens.NEW_DRAWING.route) },
                onDrawingClick = { navController.navigate(Screens.DRAWING.route) },
            )
        }
        composable(Screens.NEW_DRAWING.route) {
            NewDrawingScreen(
                viewModel = getViewModel(),
                onNewDrawingDismissed = navController::navigateUp
            )
        }
    }
}