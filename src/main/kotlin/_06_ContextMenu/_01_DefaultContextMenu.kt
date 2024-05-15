package _06_ContextMenu

/**
 * [Default context menu]
 * There is out-of-the box context menu support for TextField and Selectable text.
 *
 * To enable standard context menu for a TextField you just need to put it inside DesktopMaterialTheme:
 */
/*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication(title = "Context menu") {
    val text = remember { mutableStateOf("Hello!") }
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text(text = "Input") }
    )
}
*/

/**
 * Standard context menu for TextField contains the following items based on text selection: Copy, Cut, Paste, Select All.
 * Context menu for text contains just Copy action:
 * Enabling standard context menu for a Text component is similar - you just need to make it selectable:
 */

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication(title = "Context menu") {
    SelectionContainer {
        Text("Hello World!")
    }
}