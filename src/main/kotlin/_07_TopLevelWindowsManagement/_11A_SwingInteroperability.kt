package _07_TopLevelWindowsManagement

import androidx.compose.ui.awt.ComposeWindow
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.SwingUtilities

/**
 * Because Compose for Desktop uses Swing under the hood, it is possible to create a window using Swing directly:
 */
fun main() = SwingUtilities.invokeLater {
    ComposeWindow().apply {
        size = Dimension(300, 300)
        defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        setContent {
            // Content
        }
        isVisible = true
    }
}