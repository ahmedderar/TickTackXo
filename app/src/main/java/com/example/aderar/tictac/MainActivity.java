package com.example.aderar.tictac;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.R.attr.x;
import static com.example.aderar.tictac.R.drawable.o;

public class MainActivity extends AppCompatActivity {

    ImageButton b11;
    ImageButton b12;
    ImageButton b13;

    ImageButton b21;
    ImageButton b22;
    ImageButton b23;

    ImageButton b31;
    ImageButton b32;
    ImageButton b33;
    Game xoGame;
    int status = 0;

    private boolean player1Turn = true;
    private boolean player2Turn = false;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //    bundle = savedInstanceState;

        xoGame = new Game();


    }

    protected void showResult() {
        TextView textView = (TextView) findViewById(R.id.result);
        Button button = (Button) findViewById(R.id.reset);
        // if (status == 0)
        //    textView.setText("Game is Running");
        if (status == 1) {
            textView.setText("Player 1 Won");
            button.setVisibility(1);
        } else if (status == 2) {
            textView.setText("Player 2 Won");
            button.setVisibility(1);
        } else if (status == 3) {
            textView.setText("Game is a Tie");
            button.setVisibility(1);
        }
    }

    protected void reset(View view) {
      restartActivity(this);
      // this.recreate();
        //xoGame.resetGame();
        //setContentView(R.layout.activity_main);
    }
    protected static void restartActivity(Activity act){

        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();

    }



    //to play the computer
    protected void viewO(int r, int c) {


        b11 = (ImageButton) findViewById(R.id.iB11);
        //  b11.setOnClickListener(imgButtonHandler11);

        b12 = (ImageButton) findViewById(R.id.iB12);
        //  b12.setOnClickListener(imgButtonHandler12);

        b13 = (ImageButton) findViewById(R.id.iB13);
        // b13.setOnClickListener(imgButtonHandler13);

        b21 = (ImageButton) findViewById(R.id.iB21);
        // b21.setOnClickListener(imgButtonHandler21);

        b22 = (ImageButton) findViewById(R.id.iB22);
        // b22.setOnClickListener(imgButtonHandler22);

        b23 = (ImageButton) findViewById(R.id.iB23);
        //  b23.setOnClickListener(imgButtonHandler23);

        b31 = (ImageButton) findViewById(R.id.iB31);
        //  b31.setOnClickListener(imgButtonHandler31);

        b32 = (ImageButton) findViewById(R.id.iB32);
        // b32.setOnClickListener(imgButtonHandler32);

        b33 = (ImageButton) findViewById(R.id.iB33);
        //b33.setOnClickListener(imgButtonHandler33);

        if (r == 1 && c == 1)
            b11.setImageResource(o);
        //b11.setBackgroundResource(R.drawable.o);
        if (r == 1 && c == 2)
            b12.setImageResource(o);
        if (r == 1 && c == 3)
            b13.setImageResource(o);
        if (r == 2 && c == 1)
            b21.setImageResource(o);
        if (r == 2 && c == 2)
            b22.setImageResource(o);
        if (r == 2 && c == 3)
            b23.setImageResource(o);
        if (r == 3 && c == 1)
            b31.setImageResource(o);
        if (r == 3 && c == 2)
            b32.setImageResource(o);
        if (r == 3 && c == 3)
            b33.setImageResource(o);

    }

    // get position fron view
    protected Position getPosition(View view) {
        ImageButton imageButton;
        Position position = new Position();
        if (view.getId() == R.id.iB11) {
            position.row = 1;
            position.column = 1;
        }
        if (view.getId() == R.id.iB12) {
            position.row = 1;
            position.column = 2;
        }
        if (view.getId() == R.id.iB13) {
            position.row = 1;
            position.column = 3;
        }
        if (view.getId() == R.id.iB21) {
            position.row = 2;
            position.column = 1;
        }
        if (view.getId() == R.id.iB22) {
            position.row = 2;
            position.column = 2;
        }
        if (view.getId() == R.id.iB23) {
            position.row = 2;
            position.column = 3;
        }
        if (view.getId() == R.id.iB31) {
            position.row = 3;
            position.column = 1;
        }
        if (view.getId() == R.id.iB32) {
            position.row = 3;
            position.column = 2;
        }
        if (view.getId() == R.id.iB33) {
            position.row = 3;
            position.column = 3;
        }
        return position;
    }

    protected void play(View view) {
        // flag =true;
        Position position = getPosition(view);
        ImageButton imageButton = (ImageButton) view;
        if (status < 1) {
            if (player1Turn) {
                if (xoGame.playTurn(Value.X, position.row, position.column)) {
                    player1Turn = false;
                    player2Turn = true;
                    //b11.onVisibilityAggregated(true);
                    Position positionOfO;
                    imageButton.setImageResource(R.drawable.close);
                    status = xoGame.checkWin();
                    // O turn to play
                    if ((xoGame.count < 4)&&(status < 1)) {
                        positionOfO = xoGame.playAuro();
                        viewO(positionOfO.row, positionOfO.column);
                        // wait(100);
                        player1Turn = true;
                        player2Turn = false;
                    }//else position = new Position();
                    status = xoGame.checkWin();
                    /**
                     if (status == 1)
                     Toast.makeText(context, "Player 1 Won", Toast.LENGTH_LONG).show();
                     else if (status == 2)
                     Toast.makeText(context, "Player 2 Won", Toast.LENGTH_LONG).show();
                     else if (status == 3)
                     Toast.makeText(context, "Game is a Tie", Toast.LENGTH_LONG).show();
                     **/
                    showResult();

                }
            }
        }
    }

}
