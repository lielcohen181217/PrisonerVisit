package com.liel.prisonervisit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewDay extends AppCompatActivity implements View.OnClickListener {
    TextView prisonname;
    TextView NumberPrisoner;
    TextView beginMeeting;
    TextView endMeetting;
    Button setmeeting;
    Meeting m;
 private   DatabaseReference myRef;

 private   FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_day);
        beginMeeting=findViewById(R.id.begingmeeting);
        endMeetting=findViewById(R.id.endmeeting);
        setmeeting=findViewById(R.id.setmeeting);
        m=new Meeting();
        m=(Meeting)getIntent().getExtras().getSerializable("meetingday");
        String s="start: "+m.startHour+":00";
        String e="end: "+m.finisgHour+":00";
        beginMeeting.setText(s);
        endMeetting.setText(e);
        setmeeting.setOnClickListener(this);
        database = FirebaseDatabase.getInstance();
        String key=(String) getIntent().getExtras().getString("key");
        myRef = database.getReference("prisoners/"+key) ;
        prisonname=findViewById(R.id.PrisonName);
        NumberPrisoner=findViewById(R.id.numberprisoner);
        this.returndetails();



    }
    public void returndetails(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           Prisoner p=dataSnapshot.getValue(Prisoner.class);
             prisonname.setText("At: "+p.namePrison);
             NumberPrisoner.setText("With:"+p.prisonerId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        final int visitid=(Integer)getIntent().getExtras().getInt("visitorid");
        final String prisonerid=(String)getIntent().getExtras().getString("prisonerid");
      final  int prisoneIDINT=Integer.parseInt(prisonerid);
      myRef.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot data:dataSnapshot.child("meetings").child(m.day).getChildren())
                   {
                  if(data.child("startHour").getValue(Integer.class)==m.startHour&&data.child("finisgHour").getValue(Integer.class)==m.finisgHour){
                      Meeting meet=new Meeting(m.day,m.startHour, m.finisgHour, true, visitid,prisoneIDINT );
                      myRef.child("meetings").child(m.day).child(data.getKey()).setValue(meet);
                      Toast.makeText(ViewDay.this, "The meeting is determined", Toast.LENGTH_SHORT).show();
                     finish();
                  }

              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });

    }
}
