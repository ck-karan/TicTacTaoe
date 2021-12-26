package com.dack.tictactaoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Grid Win Positions
    int [][] winPos = {{0,1,2}, {3,4,5}, {6,7,8},
                        {0,3,6}, {1,4,7}, {2,5,8},
                        {0,4,8}, {2,4,6}};

    //Grid locations
    //Assume :  0 - blank | 1 - X | 2 - O
    int locations[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    int activePlayer = 1;
    boolean activeGame = true;

    public void playerTap(View view){

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(!activeGame){
            TextView status = findViewById(R.id.status);
            status.setText("Tap to play...!");
            gameReset(view);
        }
        //adding images to grid
        else if(locations[tappedImage] == 0 && activeGame){
            locations[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            TextView status = findViewById(R.id.status);
            if(activePlayer == 1){
                img.setImageResource(R.drawable.x);
                activePlayer = 2;
                status.setText("O's turn - Tap to play...");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer = 1;
                status.setText("X's turn - Tap to play...");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check win
        for(int[] winPosition : winPos){
            if(locations[winPosition[0]] == locations[winPosition[1]]
                    && locations[winPosition[1]] == locations[winPosition[2]]
                    && locations[winPosition[2]] != 0){

                activeGame = false;
                String winner;
                if(locations[winPosition[0]] == 1){
                    winner = "X has Won...! (Tap to restart)";
                }
                else{
                    winner = "O has Won...! (Tap to restart)";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winner);
            }
        }
    }

    private void gameReset(View view) {
        activeGame = true;
        activePlayer = 1;
        for(int i=0; i<locations.length; i++){
            locations[i] = 0;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}