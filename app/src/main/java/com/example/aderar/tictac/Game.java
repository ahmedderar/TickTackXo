package com.example.aderar.tictac;

import java.util.Random;

/**
 * Created by Admin on 22-Dec-16.
 */

class Game {

    GameBoard xoboard;
    private boolean centerToken = false;
    protected static int callingNO;
    protected static int count;

    // private int playerNo;
    // private int noPlayers;
    private boolean player1Won;
    private boolean player2Won;

    private boolean player1turn;
    private boolean player2turn;

    protected int rowO = 0;
    protected int columnO = 0;

    //Constractor
    protected Game() {
        xoboard = new GameBoard();
        //  noPlayers = 0;
        callingNO = 0;
        //  playerNo= 1 ;
        player1Won = false;
        player2Won = false;
        player1turn = false;
        player2turn = false;
        count = 0;
    }

    // check i f the game is ended with no winner
    protected boolean boardFull() {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
               if (xoboard.getBox(j, i).isEmpty)
                return false;
            }
        }
        return true;
    }

    // to find the winner
    protected boolean didWin(Value value) {
        if (((xoboard.getBox(1, 1).getValue()) == value) && ((xoboard.getBox(1, 2).getValue()) == value) && ((xoboard.getBox(1, 3).getValue()) == value))
            return true;
        if (((xoboard.getBox(2, 1).getValue()) == value) && ((xoboard.getBox(2, 2).getValue()) == value) && ((xoboard.getBox(2, 3).getValue()) == value))
            return true;
        if (((xoboard.getBox(3, 1).getValue()) == value) && ((xoboard.getBox(3, 2).getValue()) == value) && ((xoboard.getBox(3, 3).getValue()) == value))
            return true;

        if (((xoboard.getBox(1, 1).getValue()) == value) && ((xoboard.getBox(2, 1).getValue()) == value) && ((xoboard.getBox(3, 1).getValue()) == value))
            return true;
        if (((xoboard.getBox(1, 2).getValue()) == value) && ((xoboard.getBox(2, 2).getValue()) == value) && ((xoboard.getBox(3, 2).getValue()) == value))
            return true;
        if (((xoboard.getBox(1, 3).getValue()) == value) && ((xoboard.getBox(2, 3).getValue()) == value) && ((xoboard.getBox(3, 3).getValue()) == value))
            return true;

        if (((xoboard.getBox(1, 1).getValue()) == value) && ((xoboard.getBox(2, 2).getValue()) == value) && ((xoboard.getBox(3, 3).getValue()) == value))
            return true;
        if (((xoboard.getBox(1, 3).getValue()) == value) && ((xoboard.getBox(2, 2).getValue()) == value) && ((xoboard.getBox(3, 1).getValue()) == value))
            return true;
        else
            return false;
    }

    //to avoid dealing with gameboard
    protected boolean checkEmpty(int r, int c) {
        if (xoboard.getBox(r, c).isEmpty)
            return true;
        else return false;
    }

    // find the status of the game
    protected int checkWin() {

        player1Won = didWin(Value.X);
        if (player1Won) {
            // System.out.println("Player 1 Won");
            return 1;
        }
        if (boardFull()) {
            //  System.out.println(" NO Winner Game is a tie ");
            return 3;
        }

        player2Won = didWin(Value.O);
        if (player2Won) {
            //System.out.println("Player 2 Won");
            return 2;
        }
        return 0;
    }

    // play 1 or 2 in case of manual players
    protected boolean playTurn(Value v, int row, int column) {

        if (!(checkEmpty(row, column))) {
            return false;
        }
        xoboard.setBox(row, column, v);
        return true;
    }

    // Case of playing with the computer
    protected Position playAuro() {
        Position position = bestPlay(count);
        count++;
        if (position.row == 0) {
            // System.out.println("Used Random there some thing wrong");
            position = getRandPosition();
        }

        if (!(checkEmpty(position.row, position.column))) {
            return this.playAuro();

        }
        xoboard.setBox(position.row, position.column, Value.O);
        //GameDisplay.display(this);
        player1turn = true;
        player2turn = false;
        return position;
    }

    // To know best play to win
    protected Position bestPlay(int count) {
        Position position;
        if (count == 0) {
            if (xoboard.getBox(2, 2).isEmpty) {
                centerToken = true;
                return xoboard.getBox(2, 2).position;
            } else {
                position = getRandomCorner();
                return position;
            }

        } else if (count == 1) {
            position = PlaytoWinOrBlock("BLOCK");
            return position;

        } else {
            position = PlaytoWinOrBlock("win");
            if (!(position.row == 0))
                return position;
            else {
                position = PlaytoWinOrBlock("Block");
                if (!(position.row == 0))
                    return position;
                else {
                    Game.callingNO = 0;
                    return this.getRandomCorner();
                }
            }
        }

    }

    private Position PlaytoWinOrBlock(String status) {
        Value value;
        if (status.equalsIgnoreCase("WIN"))
            value = Value.O;
        else value = Value.X;


        if (((centerToken) && status.equalsIgnoreCase("win")) || ((!centerToken) && status.equalsIgnoreCase("block"))) {
            // Diagonal one check
            if (((xoboard.getBox(1, 1).getValue()) == value) && (xoboard.getBox(3, 3).isEmpty))
                return xoboard.getBox(3, 3).position;
            if (((xoboard.getBox(3, 3).getValue()) == value) && (xoboard.getBox(1, 1).isEmpty))
                return xoboard.getBox(1, 1).position;
            //diagonal 2
            if (((xoboard.getBox(1, 3).getValue()) == value) && (xoboard.getBox(3, 1).isEmpty))
                return xoboard.getBox(3, 1).position;
            if (((xoboard.getBox(3, 1).getValue()) == value) && (xoboard.getBox(1, 3).isEmpty))
                return xoboard.getBox(1, 3).position;
            //check  row 2
            if (((xoboard.getBox(2, 1).getValue()) == value) && (xoboard.getBox(2, 3).isEmpty))
                return xoboard.getBox(2, 3).position;
            if (((xoboard.getBox(2, 3).getValue()) == value) && (xoboard.getBox(2, 1).isEmpty))
                return xoboard.getBox(2, 1).position;
        }
        // row 1
        if (((xoboard.getBox(1, 1).getValue()) == value) && ((xoboard.getBox(1, 2).getValue()) == value) && (xoboard.getBox(1, 3).isEmpty))
            return xoboard.getBox(1, 3).position;
        if (((xoboard.getBox(1, 1).getValue()) == value) && ((xoboard.getBox(1, 3).getValue()) == value) && (xoboard.getBox(1, 2).isEmpty))
            return xoboard.getBox(1, 2).position;
        if (((xoboard.getBox(1, 2).getValue()) == value) && ((xoboard.getBox(1, 3).getValue()) == value) && (xoboard.getBox(1, 1).isEmpty))
            return xoboard.getBox(1, 1).position;
        // row 3
        if (((xoboard.getBox(3, 1).getValue()) == value) && ((xoboard.getBox(3, 2).getValue()) == value) && (xoboard.getBox(3, 3).isEmpty))
            return xoboard.getBox(3, 3).position;
        if (((xoboard.getBox(3, 1).getValue()) == value) && ((xoboard.getBox(3, 3).getValue()) == value) && (xoboard.getBox(3, 2).isEmpty))
            return xoboard.getBox(3, 2).position;
        if (((xoboard.getBox(3, 2).getValue()) == value) && ((xoboard.getBox(3, 3).getValue()) == value) && (xoboard.getBox(3, 1).isEmpty))
            return xoboard.getBox(3, 1).position;
        // colume 1
        if (((xoboard.getBox(1, 1).getValue()) == value) && ((xoboard.getBox(2, 1).getValue()) == value) && (xoboard.getBox(3, 1).isEmpty))
            return xoboard.getBox(3, 1).position;
        if (((xoboard.getBox(1, 1).getValue()) == value) && ((xoboard.getBox(3, 1).getValue()) == value) && (xoboard.getBox(2, 1).isEmpty))
            return xoboard.getBox(2, 1).position;
        if (((xoboard.getBox(2, 1).getValue()) == value) && ((xoboard.getBox(3, 1).getValue()) == value) && (xoboard.getBox(1, 1).isEmpty))
            return xoboard.getBox(1, 1).position;
        // colume 2
        if (((xoboard.getBox(1, 2).getValue()) == value) && ((xoboard.getBox(2, 2).getValue()) == value) && (xoboard.getBox(3, 2).isEmpty))
            return xoboard.getBox(3, 2).position;
        if (((xoboard.getBox(2, 2).getValue()) == value) && ((xoboard.getBox(3, 2).getValue()) == value) && (xoboard.getBox(1, 2).isEmpty))
            return xoboard.getBox(1, 2).position;
        if (((xoboard.getBox(1, 2).getValue()) == value) && ((xoboard.getBox(3, 2).getValue()) == value) && (xoboard.getBox(2, 2).isEmpty))
            return xoboard.getBox(2, 2).position;
        //colume 3
        if (((xoboard.getBox(1, 3).getValue()) == value) && ((xoboard.getBox(2, 3).getValue()) == value) && (xoboard.getBox(3, 3).isEmpty))
            return xoboard.getBox(3, 3).position;
        if (((xoboard.getBox(1, 3).getValue()) == value) && ((xoboard.getBox(3, 3).getValue()) == value) && (xoboard.getBox(2, 3).isEmpty))
            return xoboard.getBox(2, 3).position;
        if (((xoboard.getBox(2, 3).getValue()) == value) && ((xoboard.getBox(3, 3).getValue()) == value) && (xoboard.getBox(1, 3).isEmpty))
            return xoboard.getBox(1, 3).position;

        return new Position();
    }

    private Position getRandomCorner() {
        Position position = new Position();
        callingNO++;
        if (callingNO > 5)
            return new Position();

        Random rand = new Random();
        int randNumber = rand.nextInt(4) + 1;
        switch (randNumber) {
            case 1:
                if (checkEmpty(3, 3))
                    position = xoboard.getBox(3, 3).position;
                else position = getRandomCorner();
                break;
            case 2:
                if (this.checkEmpty(1, 3))
                    position = xoboard.getBox(1, 3).position;
                else position = this.getRandomCorner();
                break;
            case 3:
                if (this.checkEmpty(3, 1))
                    position = xoboard.getBox(3, 1).position;
                else position = this.getRandomCorner();
                break;
            case 4:
                if (this.checkEmpty(1, 1))
                    position = xoboard.getBox(1, 1).position;
                else position = this.getRandomCorner();
                break;

        }
        return position;
    }

    protected void resetGame(){
        xoboard.resetBoard();
        centerToken = false;
         callingNO = 0;
        count = 0;

        // private int playerNo;
        // private int noPlayers;
        player1Won = false;
        player2Won = false;

    }

    // To play random position
    protected Position getRandPosition(){

        Random random = new Random();
        Position position = new Position();
        position.row = random.nextInt(3) + 1;
        position.column = random.nextInt(3) + 1;
        if ( checkEmpty(position.row,position.column))
            return position;
        else return getRandPosition();
    }




}
