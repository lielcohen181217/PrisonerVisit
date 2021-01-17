package com.liel.prisonervisit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewDayHourWarden extends AppCompatActivity {
     TextView beginwork;
     TextView endwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_day_hour_warden);

        beginwork=findViewById(R.id.beginwork);
        endwork=findViewById(R.id.endwork);
        Time t=(Time)getIntent().getExtras().getSerializable("time");

        beginwork.setText("start:  "+t.startHour+":00");
        endwork.setText("end:  "+t.finisgHour+":00");




    }
}
