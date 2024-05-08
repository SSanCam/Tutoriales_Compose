package _01_Getting_started

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
@Preview
fun App() {
    MaterialTheme {
        var timeAtLocation by remember { mutableStateOf("No location selected") }
        Column {
            Text(timeAtLocation)
            Button(onClick = { timeAtLocation = "13:30" }) {
                Text("Show Time At Location")
            }
        }
    }
}
fun main() = application {
    val state = rememberWindowState(
        size = DpSize(400.dp, 250.dp),
        position = WindowPosition(300.dp, 300.dp)
    )
    Window(title = "Local Time App", onCloseRequest = ::exitApplication, state = state) {
        App()
    }
}