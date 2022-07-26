package com.xacalet.minesweeper.common.extensions

inline fun <reified T : Any> matrixOf(
    rows: Int,
    columns: Int,
    init: (Int, Int) -> T
): Array<Array<T>> =
    Array(columns) { x -> Array(rows) { y -> init(x, y) } }

inline fun <reified T : Any> Array<Array<T>>.forEach(block: (Int, Int, T) -> Unit) {
    forEachIndexed { x, column ->
        column.forEachIndexed { y, item ->
            block(x, y, item)
        }
    }
}
