package com.garcestechnology.pauljosephdarsantos.midtermquiz1;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    static int interval;
    static Timer timer;

    TextView CountDown;
    TextView NumberTxt;
    TextView ScoreTxt;
    Switch mySwitch;

    Button BtnTrue, BtnFalse;

    String boxColor, textColor;

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = 0;

        CountDown = (TextView) findViewById(R.id.countDown);
        NumberTxt = (TextView) findViewById(R.id.numberTxt);
        ScoreTxt = (TextView) findViewById(R.id.scoreTxt);

        BtnTrue = (Button) findViewById(R.id.btnTrue);
        BtnFalse = (Button) findViewById(R.id.btnFalse);
        BtnTrue.setEnabled(false);
        BtnFalse.setEnabled(false);

        BtnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boxColor.equals(textColor)) {
                    score = score + 1;
                    ScoreTxt.setText("Score: "+score);
                } else {
                    score = score - 1;
                    ScoreTxt.setText("Score: "+score);
                }
                timer.cancel();
                runAgain();
            }
        });

        BtnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!boxColor.equals(textColor)) {
                    score = score + 1;
                    ScoreTxt.setText("Score: "+score);
                } else {
                    score = score - 1;
                    ScoreTxt.setText("Score: "+score);
                }
                timer.cancel();
                runAgain();
            }
        });


        mySwitch = (Switch) findViewById(R.id.switch1);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    colorText();
                    color();
                    BtnTrue.setEnabled(true);
                    BtnFalse.setEnabled(true);
                    countDown(); // Run timer
                } else {
                    BtnTrue.setEnabled(false);
                    BtnFalse.setEnabled(false);
                    timer.cancel();
                }
            }
        });

    }

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }

    private void runAgain() {
        color();
        colorText();
        countDown();
    }

    private void countDown() {
        String secs = "11";
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = Integer.parseInt(secs);
        System.out.println(secs);
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                //System.out.println(setInterval());
                //CountDown.setText(""+setInterval());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // This code will always run on the UI thread, therefore is safe to modify UI elements.
                        CountDown.setText(""+setInterval());
                    }
                });

            }
        }, delay, period);
    }

    private void color() {
        Random random = new Random();
        int max = 5;
        int min = 1;
        int result = random.nextInt(max - min + 1) + min;

        switch(result) {
            case 5:
                CountDown.setBackgroundColor(Color.RED);
                //NumberTxt.setText("RED");
                boxColor = "RED";
                break;
            case 4:
                CountDown.setBackgroundColor(Color.BLUE);
                //NumberTxt.setText("BLUE");
                boxColor = "BLUE";
                break;
            case 3:
                CountDown.setBackgroundColor(Color.YELLOW);
                //NumberTxt.setText("YELLOW");
                boxColor = "YELLOW";
                break;
            case 2:
                CountDown.setBackgroundColor(Color.CYAN);
                //NumberTxt.setText("CYAN");
                boxColor = "CYAN";
                break;
            case 1:
                CountDown.setBackgroundColor(Color.DKGRAY);
                //NumberTxt.setText("DARK GRAY");
                boxColor = "DARK GRAY";
                break;

        } //switch
    }

    private void colorText() {
        Random random = new Random();
        int max = 5;
        int min = 1;
        int result = random.nextInt(max - min + 1) + min;

        switch(result) {
            case 5:
                NumberTxt.setText("YELLOW");
                textColor = "YELLOW";
                break;
            case 4:
                NumberTxt.setText("RED");
                textColor = "RED";
                break;
            case 3:
                NumberTxt.setText("BLUE");
                textColor = "BLUE";
                break;
            case 2:
                NumberTxt.setText("DARK GRAY");
                textColor = "DARK GRAY";
                break;
            case 1:
                NumberTxt.setText("CYAN");
                textColor = "CYAN";
                break;

        } //switch
    }

    private void checkIfCorrect() {

    }
}
