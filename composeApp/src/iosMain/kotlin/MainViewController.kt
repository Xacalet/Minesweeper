import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.window.ComposeUIViewController
import com.xacalet.minesweeper.common.ui.MainScreen
import com.xacalet.minesweeper.common.ui.theme.mineSweeperFontFamily
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
fun MainViewController() = ComposeUIViewController {
    LaunchedEffect(Unit) {
        val byteArray = resource("font/mine_sweeper.ttf").readBytes()
        mineSweeperFontFamily = FontFamily(Font("MineSweeper", byteArray))
    }
    MainScreen()
}
