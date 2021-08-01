# :bomb: Minesweeper :triangular_flag_on_post:
The classic Minesweeper game for Android with the ol' good Windows 3.1 appearance, entirely written with :sparkles:Jetpack Compose:sparkles:.

## 🚧 TODO 🚧
- Add a difficulty selector that fits with the general look-and-feel of the app. Also, figure out how to bring the difficulty system from the classic game for Windows intto a small screen environment.
- Migrate this project to KMP (at least for Android). 

## Instructions
 - The game is played on a board of 12x9 cells. 20 of these cells are hiding a mine that will explode if player uncovers them. The rest of cells are safe to uncover. Click on each cell to revel what's beneath. The goal of the game is to uncover all the cells from the board that do not contain a mine. Uncovering a mined cell will result in losing the game.
 <p align="center">
  <img alt="Captura de pantalla 2021-07-31 a las 8 46 01" src="https://user-images.githubusercontent.com/7533710/127735670-7966298e-5c6e-42fd-96b9-a10697fc02b4.png">
  <br>
  <i>The board contains the following elements: A timer, activated when the first cell is uncovered, and paused when the game is on background; The New Game button; A counter of mines left to flag (it can be less than zero if there are more flags than mines) </i>
</p>
 
 - An uncovered cell will display the number of neighbor cells that contain a mine, ranging from 1 to 8. A neighbor is any of the 8 cells sorrounding the target cell. If a cell with no close mine is revealed, it won't show any number, and the game will recursively uncover its neighbors until reaching cells with close mines or the edges of the board. 
<p align="center">
  <img width="91" alt="Captura de pantalla 2021-07-31 a las 8 46 01" src="https://user-images.githubusercontent.com/7533710/127731669-52385f72-90e8-4212-b4bb-b8710360f4ed.png">
  <br>
  <i>The number 2 on this cell means that two of the eight neighbors of this cell contain a mine</i>
</p>

 - If a cell is suspected to hide a mine, you can mark it with a flag by long-clicking the cell. Flagged cells cannot be uncovered. Long-click on a flagged cell to remove its flag. Keep in mind that: 
   - Flagging a cell does not guarantee that the marked cell will contain a mine. Be careful with marking safe cells.
   - Flagging does not take part in the game resolution. Remember that in order to win the game, you must uncover all the safe cells.
 - If you think you have flagged all the mined neighbors of a cell (having the same many flags as the numbered cell indicates), you can just click on the uncovered cell to automatically reveal the rest of close neighbors, <b>even though they contain a mine. If you have marked a safe cell by mistake, it means that one of the covered cells is an actual mine, and it will explode on doing this action</b>
<p align="center">
  <img width="227" alt="Captura de pantalla 2021-07-31 a las 8 46 01" src="https://user-images.githubusercontent.com/7533710/127733929-681c9d8d-3df3-414d-95cf-a386344e999a.png">
  <br>
  <i>Revealing the rest of uncovered mines when flags are placed on mine cells</i>
  <br><br>
  <img width="227" alt="Captura de pantalla 2021-07-31 a las 8 46 01" src="https://user-images.githubusercontent.com/7533710/127733846-edc5e617-1218-4413-b79c-5f0f3daf9540.png">
  <br>
  <i>Revealing the rest of uncovered mines when one or more flags are placed on safe cells</i>
</p>

## License
```
MIT License

Copyright (c) 2021 Alex Barceló

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
