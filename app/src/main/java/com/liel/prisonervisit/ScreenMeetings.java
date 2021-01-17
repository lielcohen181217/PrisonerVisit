package com.liel.prisonervisit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ScreenMeetings extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    ArrayList<Meeting> meetings;
AllmeetingsAdapter adaptermeeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_meetings);




        meetings=new ArrayList<>();
        meetings=(ArrayList<Meeting>)getIntent().getExtras().getSerializable("meetingsrelevant");

        listView=findViewById(R.id.tvday);
        adaptermeeting=new AllmeetingsAdapter(ScreenMeetings.this,0,0,meetings);
        listView.setAdapter(adaptermeeting);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Meeting m=meetings.get(position);
        Intent intent=new Intent(ScreenMeetings.this,ViewDay.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("meetingday",m);
        intent.putExtras(bundle);
        String pri=(String) getIntent().getExtras().getString("prisonerid");
        int vistId=(Integer) getIntent().getExtras().getInt("visitorid");
        String key=(String) getIntent().getExtras().getString("key");

        intent.putExtra("prisonerid",pri);
        intent.putExtra("visitorid",vistId);
        intent.putExtra("key",key);

        startActivity(intent);

    }
}
