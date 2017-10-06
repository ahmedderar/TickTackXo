package com.example.aderar.tictac;

/**
 * Created by Admin on 22-Dec-16.
 */

class Box {
    Position position;
    Value value;
    boolean isEmpty;

     Box(int row, int column) {
         position = new Position();
        this.position.row = row;
        this.position.column = column;
        value = null;
        isEmpty = true;
    }

     void setValue(Value value) {
        this.value = value;
        isEmpty = false;
    }

     Value getValue() {
        return value;
    }
}
