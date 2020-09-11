package com.sanggggg.tictactoe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

@Suppress("PrivatePropertyName")
class TicTacToeViewModel : ViewModel() {

    // TODO: Make your ViewModel!
    // CONSTANTS FOR views text (DO NOT FIX)
    private val PLAYER_O = "O"
    private val PLAYER_X = "X"

    private val STATUS_PLAYING = "PLAYING..."
    private val STATUS_O_WIN = "PLAYER O WIN!"
    private val STATUS_X_WIN = "PLAYER X WIN!"
    private val STATUS_DRAW = "DRAW!"

    // CONSTANTS added
    private val STATUS_O_TURN = "PLAYER O TURN"
    private val STATUS_X_TURN = "PLAYER X TURN"

    // recommended LiveData fields (fill free to fix it)
    val cells = ArrayList<MutableLiveData<String>>()
    val gameStatus = MutableLiveData<String>()
    private var whoseTurn = true
    private var done = false
    private var turn = 0

    init {
        for (i in 0..8) {
            cells.add(MutableLiveData())
        }
        restart()
    }

    // recommended function structures (fill free to fix it)
    fun clickCell(pos: Int) {
        if (done) return
        if (cells[pos].value == PLAYER_X || cells[pos].value == PLAYER_O) {
            return
        }
        if (whoseTurn){
            gameStatus.value = STATUS_X_TURN
        } else{
            gameStatus.value = STATUS_O_TURN
        }
        if (whoseTurn) {
            cells[pos].value = PLAYER_O
        } else {
            cells[pos].value = PLAYER_X
        }
        turn++
        checkWinOrNot()
        whoseTurn = !whoseTurn
    }

    fun restart() {
        gameStatus.value = STATUS_O_TURN
        for (i in 0..8) {
            cells[i].value = ""
        }
        whoseTurn = true
        done = false
        turn = 0
    }

    fun checkWinOrNot() {
        for (i in 0..2) {
            if ((cells[i].value != "" &&
                        cells[i].value == cells[i + 3].value &&
                        cells[i + 3].value == cells[i + 6].value) ||
                (cells[i * 3].value != "" &&
                        cells[i * 3].value == cells[i * 3 + 1].value &&
                        cells[i * 3 + 1].value == cells[i * 3 + 2].value) ||
                (cells[4].value != "" &&
                        cells[0].value == cells[4].value &&
                        cells[4].value == cells[8].value) ||
                (cells[4].value != "" &&
                        cells[2].value == cells[4].value &&
                        cells[4].value == cells[6].value)
            ) {
                if (whoseTurn) {
                    gameStatus.value = STATUS_O_WIN
                } else {
                    gameStatus.value = STATUS_X_WIN
                }
                done = true
                return
            }
        }
        if (turn == 9) {
            gameStatus.value = STATUS_DRAW
            done = true
            return
        }
    }
}