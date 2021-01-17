package com.liel.prisonervisit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Enter_Visitor extends AppCompatActivity implements View.OnClickListener {
    EditText idprisoner;
    EditText idvisitor;
    Button setup;
    DatabaseReference myRef;

    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter__visitor);
        idvisitor = findViewById(R.id.idvisitor);
        idprisoner=findViewById(R.id.idprisoner);
        setup=findViewById(R.id.setup);
        setup.setOnClickListener(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("prisoners") ;


    }

    @Override
    public void onClick(View v) {

        final String prisonerid=idprisoner.getText().toString();
      final  int visitorid=Integer.parseInt(idvisitor.getText().toString());



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          boolean isfound=false;
            for(DataSnapshot data:dataSnapshot.getChildren()){
               Prisoner p=data.getValue(Prisoner.class);
               if(p.prisonerId.equals(prisonerid)) {
                   if (p.visitorsId.contains(visitorid)) {
                       isfound = true;

                   }
               }
               if(isfound==true){
                   ArrayList<Meeting> m=new ArrayList<>();
                   for (int i=0;i<p.meetings.sun.size();i++){
                       if(p.meetings.sun.get(i).occupied==false) {
                           m.add(p.meetings.sun.get(i));
                       }
                   }
                   for (int i=0;i<p.meetings.mon.size();i++) {
                       if (p.meetings.mon.get(i).occupied == false) {
                           m.add(p.meetings.mon.get(i));
                       }
                   }
                   for (int i=0;i<p.meetings.tue.size();i++){
                       if (p.meetings.tue.get(i).occupied == false) {
                           m.add(p.meetings.tue.get(i));
                       }
                   }
                   for (int i=0;i<p.meetings.wed.size();i++){
                       if (p.meetings.wed.get(i).occupied == false) {
                           m.add(p.meetings.wed.get(i));
                       }
                   }
                   for (int i=0;i<p.meetings.thu.size();i++){
                       if (p.meetings.thu.get(i).occupied == false) {
                           m.add(p.meetings.thu.get(i));
                       }
                   }
                   for (int i=0;i<p.meetings.fri.size();i++){
                       if (p.meetings.fri.get(i).occupied == false) {
                           m.add(p.meetings.fri.get(i));
                       }
                   }
                   for (int i=0;i<p.meetings.sat.size();i++){
                       if (p.meetings.sat.get(i).occupied == false) {
                           m.add(p.meetings.sat.get(i));
                       }
                   }



                 Intent   intent=new Intent(Enter_Visitor.this,ScreenMeetings.class);
                   Bundle bundle =new Bundle();
                   bundle.putSerializable("meetingsrelevant",m);
                   intent.putExtras(bundle);
                   intent.putExtra("prisonerid",prisonerid);
                   intent.putExtra("visitorid",visitorid);
                   intent.putExtra("key",p.getKey());

                   startActivity(intent);


               }




              }
              if(isfound==false){
                  Toast.makeText(Enter_Visitor.this, "You'r not visitor or the prisoner not exist ", Toast.LENGTH_SHORT).show();
              }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}