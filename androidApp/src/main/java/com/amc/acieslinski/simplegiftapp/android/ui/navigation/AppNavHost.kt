package com.amc.acieslinski.simplegiftapp.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amc.acieslinski.simplegiftapp.android.ui.account.RegistrationScreen
import com.amc.acieslinski.simplegiftapp.android.ui.drawing.DrawingScreen
import com.amc.acieslinski.simplegiftapp.android.ui.scanner.ScannerScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var scannerCallback: ((String) -> Unit)? = null
    fun onCodeScanned(code: String) {
        scannerCallback?.invoke(code)
        navController.navigateUp()
    }

    NavHost(
        navController = navController,
        startDestination = Screens.REGISTRATION.route,
        modifier = modifier,
    ) {
        composable(Screens.REGISTRATION.route) {
            RegistrationScreen()
        }
        composable(Screens.QR_SCANNER.route) {
            ScannerScreen(::onCodeScanned)
        }
        composable(Screens.DRAWING.route) {
            val navScanner = object : ScannerNav {
                override fun startScannerScreen(callback: (String) -> Unit) {
                    scannerCallback = callback
                    navController.navigate(Screens.QR_SCANNER.route)
                }
            }
            DrawingScreen(navScanner, getViewModel())
        }
    }
}