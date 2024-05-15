package _03_MouseEventsAndHover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.window.singleWindowApplication

/**
 * [Listenining raw events in commonMain via Modifier.pointerInput]
 *
 * In the snippets above we use Modifier.onPointerEvent, which is a helper function to subscribe to some type of pointer events.
 * It is a shorter variant of Modifier.pointerInput. For now it is experimental, and desktop-only (you can't use it in commonMain code).
 * If you need to subscribe to events in commonMain or you need stable API, you can use Modifier.pointerInput:
 */

fun main() = singleWindowApplication {
    val list = remember { mutableStateListOf<String>() }

    Column(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        val position = event.changes.first().position
                        // on every relayout Compose will send synthetic Move event,
                        // so we skip it to avoid event spam
                        if (event.type != PointerEventType.Move) {
                            list.add(0, "${event.type} $position")
                        }
                    }
                }
            },
    ) {
        for (item in list.take(20)) {
            Text(item)
        }
    }
}

