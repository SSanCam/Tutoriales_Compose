package _03_MouseEventsAndHover

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.isAltPressed
import androidx.compose.ui.input.pointer.isShiftPressed
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication

/**
 * [New experimental onClick handlers (only for Desktop-JVM platform)]
 * Modifier.onClick provides independent callbacks for clicks, double clicks, long clicks. It handles clicks originated
 * only from pointer events and accessibility click event is not handled out of a box.
 *
 * Each onClick can be configured to target specific pointer events (using matcher: PointerMatcher and keyboardModifiers:
 * PointerKeyboardModifiers.() -> Boolean). matcher can be specified to choose what mouse button should trigger a click.
 * keyboardModifiers allows for filtering pointer events which have specified keyboardModifiers pressed.
 *
 * Multiple onClick modifiers can be chained to handle different clicks with different conditions (matcher and keyboard modifiers).
 * Unlike clickable, onClick doesn't have a default Modifier.indication, Modifier.semantics, and it doesn't trigger a
 * click event when Enter pressed. These modifiers need to be added separately if necessary.
 * The most generic (with the least number of conditions) onClick handlers should be declared before other to ensure
 * correct events propagation.
 */

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
fun main() = singleWindowApplication {
    Column {
        var topBoxText by remember { mutableStateOf("Click me\nusing LMB or LMB + Shift") }
        var topBoxCount by remember { mutableStateOf(0) }
        // No indication on interaction
        Box(modifier = Modifier.size(200.dp, 100.dp).background(Color.Blue)
            // the most generic click handler (without extra conditions) should be the first one
            .onClick {
                // it will receive all LMB clicks except when Shift is pressed
                println("Click with primary button")
                topBoxText = "LMB ${topBoxCount++}"
            }.onClick(
                keyboardModifiers = { isShiftPressed } // accept clicks only when Shift pressed
            ) {
                // it will receive all LMB clicks when Shift is pressed
                println("Click with primary button and shift pressed")
                topBoxCount++
                topBoxText = "LMB + Shift ${topBoxCount++}"
            }
        ) {
            AnimatedContent(
                targetState = topBoxText,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = it, textAlign = TextAlign.Center)
            }
        }

        var bottomBoxText by remember { mutableStateOf("Click me\nusing LMB or\nRMB + Alt") }
        var bottomBoxCount by remember { mutableStateOf(0) }
        val interactionSource = remember { MutableInteractionSource() }
        // With indication on interaction
        Box(modifier = Modifier.size(200.dp, 100.dp).background(Color.Yellow)
            .onClick(
                enabled = true,
                interactionSource = interactionSource,
                matcher = PointerMatcher.mouse(PointerButton.Secondary), // Right Mouse Button
                keyboardModifiers = { isAltPressed }, // accept clicks only when Alt pressed
                onLongClick = { // optional
                    bottomBoxText = "RMB Long Click + Alt ${bottomBoxCount++}"
                    println("Long Click with secondary button and Alt pressed")
                },
                onDoubleClick = { // optional
                    bottomBoxText = "RMB Double Click + Alt ${bottomBoxCount++}"
                    println("Double Click with secondary button and Alt pressed")
                },
                onClick = {
                    bottomBoxText = "RMB Click + Alt ${bottomBoxCount++}"
                    println("Click with secondary button and Alt pressed")
                }
            )
            .onClick(interactionSource = interactionSource) { // use default parameters
                bottomBoxText = "LMB Click ${bottomBoxCount++}"
                println("Click with primary button (mouse left button)")
            }
            .indication(interactionSource, LocalIndication.current)
        ) {
            AnimatedContent(
                targetState = bottomBoxText,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = it, textAlign = TextAlign.Center)
            }
        }
    }
}