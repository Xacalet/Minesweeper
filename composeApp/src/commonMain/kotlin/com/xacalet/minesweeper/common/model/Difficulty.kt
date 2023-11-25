package com.xacalet.minesweeper.common.model

sealed class Difficulty(val boardSize: BoardDimension, val mineCount: Int) {
    object Beginner : Difficulty(BoardDimension(rows = 12, columns = 9), 20)
    object Intermediate : Difficulty(BoardDimension(rows = 16, columns = 16), 40)
    object Expert : Difficulty(BoardDimension(rows = 16, columns = 30), 99)
    class Custom(rows: Int, columns: Int, mineCount: Int) :
        Difficulty(BoardDimension(rows, columns), mineCount)
}
