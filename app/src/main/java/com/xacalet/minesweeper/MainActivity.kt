package com.xacalet.minesweeper

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.xacalet.minesweeper.model.Game
import com.xacalet.minesweeper.model.Point
import com.xacalet.minesweeper.ui.component.Cell
import com.xacalet.minesweeper.ui.component.NumericDisplay
import com.xacalet.minesweeper.ui.foundation.BevelType
import com.xacalet.minesweeper.ui.foundation.bevel
import com.xacalet.minesweeper.ui.theme.MineSweeperTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MineSweeperTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Board()
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@Composable
fun Board() {
    //Make a fix size of 3x3
    val boardTable = remember { Game(Game.Difficulty.Beginner) }
    val boardState = boardTable.boardState.collectAsState().value

    ConstraintLayout(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.LightGray)
            .bevel(2.dp)
            .padding(4.dp)
    ) {
        val (score, board) = createRefs()

        // Score
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .bevel(width = 1.dp, type = BevelType.Lowered)
                .constrainAs(score) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumericDisplay(0)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier.size(28.dp),
                onClick = { /*TODO*/ }
            ) {
                //TODO
            }
            Spacer(modifier = Modifier.weight(1f))
            NumericDisplay(1)
        }

        // Board
        Column(
            modifier = Modifier
                .background(Color.Gray)
                .bevel(1.dp, type = BevelType.Lowered)
                .padding(end = 1.dp, bottom = 1.dp)
                .constrainAs(board) {
                    top.linkTo(score.bottom, margin = 4.dp)
                    width = Dimension.wrapContent
                }
        ) {
            boardState.forEachIndexed { x, column ->
                Row {
                    column.forEachIndexed { y, cellState ->
                        Cell(Modifier.size(32.dp), cellState, {
                            boardTable.uncoverCell(Point(x, y))
                        }, {
                            boardTable.setFlag(Point(x, y))
                        })
                    }
                }
            }
        }
    }
}



@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@Preview
@Composable
fun BoardPreview() {
    Board()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MineSweeperTheme {

    }
}
