package com.example.aderar.tictac;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22-Dec-16.
 */

class GameBoard {
    private List<Box> boxes ;

    GameBoard() {
        boxes = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                Box box = new Box(j, i);
                boxes.add(box);
            }
        }
    }

    protected Box getBox(int row, int column) {

        for (Box targetBox : boxes) {
            if (targetBox.position.row == row && targetBox.position.column == column)
                return targetBox;
        }
        return new Box(0,0);
    }

    protected void setBox(int row, int column, Value value) {
        getBox(row, column).setValue(value);
    }
    protected void resetBoard() {
        for (int j = 0; j < 9; j++) {
            boxes.get(j).isEmpty = false;
            boxes.get(j).value = null;
        }
    }




}
