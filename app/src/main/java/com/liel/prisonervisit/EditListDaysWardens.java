package com.liel.prisonervisit;

import android.content.Intent;
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

public class EditListDaysWardens extends AppCompatActivity implements View.OnClickListener {

    EditText day;
    EditText start;
    EditText end;
    Button add;
    Button viewwork;
String key;
    DatabaseReference myRef;

    FirebaseDatabase database;
    ArrayList<Time> works=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list_days_wardens);
        database=FirebaseDatabase.getInstance();
        key=(String)getIntent().getExtras().getString("key");
        myRef=database.getReference("wardens/"+key);
        this.retrivedata();
        day=findViewById(R.id.EditDayWork);
        start=findViewById(R.id.EditStartWork);
        end=findViewById(R.id.EditEndWork);
        add=findViewById(R.id.AddWork);
        viewwork=findViewById(R.id.Viewschedule);
        add.setOnClickListener(this);
        viewwork.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if(add==v){
            String d=day.getText().toString();
            int s=Integer.parseInt(start.getText().toString());
            int e=Integer.parseInt(end.getText().toString());
            Time t=new Time(s,e,d);
            works.add(t);
            myRef.child("schedule").setValue(works);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(viewwork==v){
            Intent intent=new Intent(this,ListScheduleWarden.class);
            intent.putExtra("key",key);
            startActivity(intent);

        }

    }
    public void retrivedata(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Warden w=dataSnapshot.getValue(Warden.class);
                if(w.schedule.size()>0){
                    for (int i=0;i<w.schedule.size();i++){
                        works.add(w.schedule.get(i));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
