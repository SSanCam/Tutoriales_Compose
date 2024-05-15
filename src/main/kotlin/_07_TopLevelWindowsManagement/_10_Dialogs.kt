package _07_TopLevelWindowsManagement

/**
 * Dialogs
 * There are two types of window – modal and regular. Below are the functions for creating each:
 *
 * Window – regular window type.
 * DialogWindow – modal window type. This type locks its parent window until the user is finished working with it and
 * closes the modal window.
 */

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.*

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
    ) {
        var isDialogOpen by remember { mutableStateOf(false) }

        Button(onClick = { isDialogOpen = true }) {
            Text(text = "Open dialog")
        }

        if (isDialogOpen) {
            DialogWindow(
                onCloseRequest = { isDialogOpen = false },
                state = rememberDialogState(position = WindowPosition(Alignment.Center))
            ) {
                // TODO("Contenido del diálogo")
            }
        }
    }
}