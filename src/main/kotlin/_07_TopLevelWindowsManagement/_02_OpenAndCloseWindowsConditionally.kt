package _07_TopLevelWindowsManagement

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay

/**
 * Open and close windows (conditionally)
 * You can also close/open windows using a simple if statement.
 *
 * When the Window leaves the composition (isPerformingTask becomes false) – the native window automatically closes.
 */
fun main() = application {
    var isPerformingTask by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // Do some heavy lifting
        // Una vez pasado éste delay, su condición pasará a ser false y se cerrará la ventana.
        isPerformingTask = false
    }

    if (isPerformingTask) {
        Window(onCloseRequest = ::exitApplication) {
            Text("Performing some tasks. Please wait!")
        }
    } else {
        Window(onCloseRequest = ::exitApplication) {
            Text("Hello, World!")
        }
    }
}