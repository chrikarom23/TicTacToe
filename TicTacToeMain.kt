package org.example

import java.util.*

fun main() {
    val game = TicTacToe()
    game.startGame()
}

class TicTacToe(){
    private val board by lazy{
        arrayOf(mutableListOf<Char>('_','_','_'),mutableListOf<Char>('_','_','_'),mutableListOf<Char>('_','_','_'))
    }
    private val scanner = Scanner(System.`in`)
    private var winnerCalled = false;

    fun startGame(){
        while(true) {
            println("Start Game?(Y/N): ")
            var play = scanner.nextLine()
            if (play[0] == 'Y' || play[0] == 'y') {
                playGame()
            } else {
                break;
            }
        }
    }

    fun playGame(){
        var round = 0
        while(!winnerCalled){
            printBoard()
                while (true) {
                    print("\nPlayer ${round%2+1}'s Move(Format: x y): ")
                    var move = scanner.nextLine()
                    val xy = move.trim().filter { it in '0'..'9' }
                    if (xy.isEmpty() || xy.length>2) {
                        println("\nInvalid Move!!!")
                        continue
                    }
                    val x = xy[0].digitToInt()
                    val y = xy[1].digitToInt()
                    if (checkValid(x, y)) {
                        board[x][y] = if(round%2==0) 'X' else 'O';
                        checkWin()
                        break;
                    } else {
                        println("\nInvalid Move!!!")
                    }
            }
            round++;
        }
    }

    private fun checkWin() {
        var countXO0 = 0
        var countXO1 = 0
        var countXO2 = 0
        for(i in board){
            var countXOx = 0
            for(j in 0..2){
                if(i[j]=='X') countXOx++
                if(i[j]=='O') countXOx--
                if(j==0 && i[j]=='X') countXO0++ else if(j==0 && i[j]=='O') countXO0--
                if(j==1 && i[j]=='X') countXO1++ else if(j==1 && i[j]=='O') countXO1--
                if(j==2 && i[j]=='X') countXO2++ else if(j==2 && i[j]=='O') countXO2--
            }
            if(countXOx==3){
                winner('X')
            }
            if(countXOx==-3){
                winner('O')
            }
        }
        if(countXO0==3) winner('X') else if(countXO0==-3) winner('Y')
        if(countXO1==3) winner('X') else if(countXO1==-3) winner('Y')
        if(countXO2==3) winner('X') else if(countXO2==-3) winner('Y')

        var countXODiag = 0
        var countXORDiag = 0
        for(i in 0..2){
                if(board[i][i]=='X') countXODiag++ else if(board[i][i]=='O') countXODiag--;
        }
        var temp = 2
        for(i in 0..2){
            if(board[i][temp]=='X') countXORDiag++ else if(board[i][temp]=='O') countXORDiag--;
            temp--;
        }

        var dashCount = 0
        for(i in board){
            for(j in i){
                if(j=='_') dashCount++
            }
        }
        if(dashCount==0) winner('D')
    }

    private fun winner(c: Char) {
        winnerCalled = true
        if(c=='X') println("First player is the winner!!!!")
        else if(c=='O')println("Second player is the winner!!!!")
        else println("The match is a draw!")
    }

    private fun checkValid(x: Int, y: Int): Boolean {
        if(x>2 || y>2) return false
        else if(board[x][y]!='_') return false
        else return true;
    }

    private fun printBoard() {
        println()
        for(i in board){
            println(i)
        }
    }

}