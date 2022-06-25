import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.xacalet.minesweeper.common.ui.MainScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Minesweeper",
        state = WindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            size = DpSize(320.dp, 550.dp),
            placement = WindowPlacement.Floating
        ),
        resizable = false
    ) {
        MainScreen()
    }
}
