package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    // 2 means empty cell
int[] gamestate = {2,2,2,2,2,2,2,2,2};
int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
boolean gameIsActive = true;

    public void dropIn(View view){
        ImageView counter = (ImageView)view;

       // counter.getTag();
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedCounter]==2 && gameIsActive) {
            gamestate[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] pos : winningPositions){
                if(gamestate[pos[0]]==gamestate[pos[1]]&&gamestate[pos[1]]==gamestate[pos[2]]&&gamestate[pos[0]]!=2)
                {
                    // winner
                    gameIsActive = false;
                    String winner = "Yellow";
                    if(gamestate[pos[0]]==1)
                    winner = "Red";


                    TextView winnerMsg = findViewById(R.id.WinnerMsg);
                    winnerMsg.setText(winner +" has won!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(view.VISIBLE);


                }else{
                    boolean gameIsOver = true;
                    for(int counterState : gamestate){
                        if(counterState==2)
                            gameIsOver =false ;

                         }
                    if(gameIsOver){
                        TextView winnerMsg = findViewById(R.id.WinnerMsg);
                        winnerMsg.setText("Its a draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(view.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(view.INVISIBLE);
        activePlayer =0;

        for(int x =0; x<gamestate.length; x++){
           gamestate[x] = 2;
        }

        GridLayout gridLayout =(GridLayout) findViewById(R.id.gridLayout);

        for(int i =0; i<gridLayout.getChildCount(); i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
