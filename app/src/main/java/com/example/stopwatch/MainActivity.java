package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageLeft;
    ImageView imageMid;
    ImageView imageRight;
    Drawable cherry;
    Drawable grape;
    Drawable pear;
    Drawable strawberry;
    Button button;
    CountEvent eventM;
    Handler handler;
    EventLeft eventL;
    Handler handlerL;
    EventRight eventR;
    Handler handlerR;
    Toast t;
    int time;
    int timeL;
    int timeR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageLeft=findViewById(R.id.imageLeft);
        imageMid=findViewById(R.id.imageMid);
        imageRight=findViewById(R.id.imageRight);
        cherry=getDrawable(R.drawable.cherry);
        grape=getDrawable(R.drawable.grape);
        pear=getDrawable(R.drawable.pear);
        strawberry=getDrawable(R.drawable.strawberry);
        button=findViewById(R.id.buttonUp);
        time=0;
        timeL=0;
        timeR=0;

        eventM=new CountEvent();
        handler= new Handler();
        eventL=new EventLeft();
        handlerL=new Handler();
        eventR=new EventRight();
        handlerR=new Handler();
        t = Toast.makeText(getApplicationContext(), "JACKPOT!!!", Toast.LENGTH_SHORT);

        if(savedInstanceState!=null){
            time=savedInstanceState.getInt("time",time);
            timeL=savedInstanceState.getInt("timeL", timeL);
            timeR=savedInstanceState.getInt("timeR",timeR);
            handler.postDelayed(eventM, 600);
            handlerL.postDelayed(eventL, 800);
            handlerR.postDelayed(eventR, 400);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button.getText().equals("START")){
                    handler.postDelayed(eventM, 600);
                    handlerL.postDelayed(eventL, 800);
                    handlerR.postDelayed(eventR, 400);
                    button.setText("STOP");
                } else if(button.getText().equals("STOP")){
                    handlerR.removeCallbacks(eventR);
                    handler.removeCallbacks(eventM);
                    handlerL.removeCallbacks(eventL);
                    button.setText("START");
                    if((imageMid.getDrawable()==imageLeft.getDrawable())&&(imageMid.getDrawable()==imageRight.getDrawable())){
                        Toast t = Toast.makeText(getApplicationContext(), "JACKPOT!!!", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }

            }
        });

    }

    private class CountEvent implements Runnable{
        @Override
        public void run(){
            time++;
            if(time==0)
                imageMid.setImageDrawable(cherry);
            else if(time==1)
                imageMid.setImageDrawable(grape);
            else if(time==2)
                imageMid.setImageDrawable(pear);
            else if(time==3)
                imageMid.setImageDrawable(strawberry);
            else if(time>3)
                time=0;
            handler.postDelayed(eventM,600);
        }
    }

    private class EventLeft implements Runnable{
        @Override
        public void run(){
            timeL++;
            if(timeL==0)
                imageLeft.setImageDrawable(cherry);
            else if(timeL==1)
                imageLeft.setImageDrawable(grape);
            else if(timeL==2)
                imageLeft.setImageDrawable(pear);
            else if(timeL==3)
                imageLeft.setImageDrawable(strawberry);
            else if(timeL>3)
                timeL=0;

            handlerL.postDelayed(eventL,800);
        }
    }

    private class EventRight implements Runnable{
        @Override
        public void run(){
            timeR++;
            if(timeR==0)
                imageRight.setImageDrawable(cherry);
            else if(timeR==1)
                imageRight.setImageDrawable(grape);
            else if(timeR==2)
                imageRight.setImageDrawable(pear);
            else if(timeR==3)
                imageRight.setImageDrawable(strawberry);
            else if(timeR>3)
                timeR=0;
            handlerR.postDelayed(eventR,400);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("time",time);
        savedInstanceState.putInt("timeL", timeL);
        savedInstanceState.putInt("timeR",timeR);
        super.onSaveInstanceState(savedInstanceState);
    }

}