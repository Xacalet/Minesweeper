import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import com.xacalet.minesweeper.common.ui.MainScreen
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window("Minesweeper") {
            Column(modifier = Modifier.fillMaxSize()) {
                MainScreen()
            }
        }
    }
}
