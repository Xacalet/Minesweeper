package com.xacalet.minesweeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.xacalet.minesweeper.common.ui.MainScreen
import com.xacalet.minesweeper.common.ui.theme.mineSweeperFontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mineSweeperFontFamily = FontFamily(Font(R.font.mine_sweeper))

        setContent {
            MainScreen()
        }
    }
}
