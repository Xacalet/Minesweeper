import androidx.compose.ui.window.Window
import com.xacalet.minesweeper.common.ui.MainScreen
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window {
            MainScreen()
        }
    }
}
