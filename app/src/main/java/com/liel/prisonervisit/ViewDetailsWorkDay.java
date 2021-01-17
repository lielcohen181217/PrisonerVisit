package com.liel.prisonervisit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewDetailsWorkDay extends AppCompatActivity {
TextView begin;
TextView end;
Time t=new Time();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details_work_day);
        begin=findViewById(R.id.bwork);
        end=findViewById(R.id.ework);
        t=(Time)getIntent().getExtras().getSerializable("time");
        begin.setText("Start: "+t.startHour+":00");
        end.setText("End: "+t.finisgHour+":00");

    }
}
