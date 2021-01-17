package com.liel.prisonervisit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScreenPrisonersmeetings extends AppCompatActivity {
AllmeetingsAdapter allmeetingsAdapter;
ArrayList<Meeting> meets=new ArrayList<>();
ListView lv;
String key;
    DatabaseReference myRef;

    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_prisonersmeetings);
        database=FirebaseDatabase.getInstance();
        lv=findViewById(R.id.tvmeets);
        key=(String)getIntent().getExtras().getString("key");
       myRef=database.getReference("prisoners/"+key);
       this.retrivedata();
       allmeetingsAdapter=new AllmeetingsAdapter(ScreenPrisonersmeetings.this,0,0,meets);
       lv.setAdapter(allmeetingsAdapter);
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Meeting m=meets.get(position);
               Intent intent=new Intent(ScreenPrisonersmeetings.this,DetailsMeetingPrisoner.class);
               Bundle bundle=new Bundle();
               bundle.putSerializable("meeting",m);
               intent.putExtras(bundle);
               startActivity(intent);
           }
       });
    }
    public void retrivedata(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Prisoner p=dataSnapshot.getValue(Prisoner.class);
           for (int i=0;i<p.meetings.sun.size();i++){
               meets.add(p.meetings.sun.get(i));
           }
                for (int i=0;i<p.meetings.mon.size();i++){
                    meets.add(p.meetings.mon.get(i));
                }
                for (int i=0;i<p.meetings.tue.size();i++){
                    meets.add(p.meetings.tue.get(i));
                }
                for (int i=0;i<p.meetings.wed.size();i++){
                    meets.add(p.meetings.wed.get(i));
                }
                for (int i=0;i<p.meetings.thu.size();i++){
                    meets.add(p.meetings.tue.get(i));
                }
                for (int i=0;i<p.meetings.fri.size();i++){
                    meets.add(p.meetings.fri.get(i));
                }
                for (int i=0;i<p.meetings.sat.size();i++){
                    meets.add(p.meetings.sat.get(i));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
