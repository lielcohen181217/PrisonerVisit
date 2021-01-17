package com.liel.prisonervisit;

import android.content.Intent;
import android.content.SyncStatusObserver;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Editprisonermeeting extends AppCompatActivity implements View.OnClickListener {
EditText Day;
EditText Begiממing;
EditText Ending;
Button AddMeeting;
Button viewmeets;
    DatabaseReference myRef;
    FirebaseDatabase database;
    String d;
    String key;
    ArrayList<Meeting> meets=new ArrayList<>();
    ArrayList<String> days;
    Schedule schedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_editprisonermeeting);
        Day=findViewById(R.id.EditDay);
        Begiממing=findViewById(R.id.EditStart);
        Ending=findViewById(R.id.EditEnd);
        key=(String)getIntent().getExtras().getString("key");
        AddMeeting=findViewById(R.id.AddNewMeeting);
        myRef = database.getReference("prisoners/" + key);
      schedule=new Schedule(new ArrayList<Meeting>(),new ArrayList<Meeting>(),new ArrayList<Meeting>(),new ArrayList<Meeting>(),new ArrayList<Meeting>(),new ArrayList<Meeting>(),new ArrayList<Meeting>());
        this.returnlistMeeting();
///        System.out.println(schedule.sun.get(0).startHour);
      viewmeets=findViewById(R.id.ViewMeetings);
      viewmeets.setOnClickListener(this);
        AddMeeting.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(AddMeeting==v) {
            myRef = database.getReference("prisoners/" + key);
            System.out.println(key);
            days = new ArrayList<>();
            days.add("sun");
            days.add("mon");
            days.add("tue");
            days.add("wed");
            days.add("thu");
            days.add("fri");
            days.add("sat");
            d = Day.getText().toString();
            int s = Integer.parseInt(Begiממing.getText().toString());
            int e = Integer.parseInt(Ending.getText().toString());
            if (days.contains(d)) {
                Meeting m = new Meeting(d, s, e, false, 0, 0);


                if(d.equals("sun"))schedule.sun.add(m);
                if(d.equals("mon"))schedule.mon.add(m);
                if(d.equals("tue"))schedule.tue.add(m);
                if(d.equals("wed"))schedule.wed.add(m);
                if(d.equals("thu"))schedule.thu.add(m);
                if(d.equals("fri"))schedule.fri.add(m);
                if(d.equals("sat"))schedule.sat.add(m);

                myRef.child("meetings").setValue(schedule);
                Toast.makeText(this, "Is added", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        if(viewmeets==v){
            Intent intent =new Intent(Editprisonermeeting.this,ScreenPrisonersmeetings.class);
            intent.putExtra("key",key);
            startActivity(intent);
        }



    }
    public void returnlistMeeting(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               schedule=dataSnapshot.child("meetings").getValue(Schedule.class);
               System.out.println(schedule.sun.get(0).day);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
