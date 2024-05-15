package _07_TopLevelWindowsManagement

/**
 * Function singleWindowApplication
 * There is a simplified function for creating a single window application:
 */

import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication {
    // Content
}

/*
Use it if:

> your application has only one window
> you don't need custom closing logic
> you don't need to change the window parameters after it is already created
*/