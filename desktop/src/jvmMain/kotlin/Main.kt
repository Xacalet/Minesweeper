import androidx.compose.material.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Minesweeper",
        state = WindowState(
            position = WindowPosition.Aligned(Alignment.Center)
        )
    ) {
        Button(onClick = {}) {

        }
    }
}
