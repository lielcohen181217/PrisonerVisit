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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EditPrisonerActivity extends AppCompatActivity implements View.OnClickListener {
EditText editIdprisoner;
EditText editprisonName;
EditText editDepartment;
EditText editcellnum;
Button editmeeting;
Button editvisitors;
Button save;

String key;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Button resetmeet;


    Prisoner p;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prisoner);
        database = FirebaseDatabase.getInstance();
        editIdprisoner=findViewById(R.id.EditIdprisoner);
        editprisonName=findViewById(R.id.Editprisonname);
        editDepartment=findViewById(R.id.Editdepartment);
        editcellnum=findViewById(R.id.Editcellnum);
        save=findViewById(R.id.saveeditprisoner);
        save.setOnClickListener(this);
        Intent intent=getIntent();
        key=intent.getExtras().getString("key");

myRef=database.getReference("prisoners/"+key);

        this.retriveData();


        resetmeet=findViewById(R.id.resetmeeting);
        resetmeet.setOnClickListener(this);
        editmeeting=findViewById(R.id.Editmeeting);
        editmeeting.setOnClickListener(this);
        editvisitors=findViewById(R.id.EditListofVisitor);
        editvisitors.setOnClickListener(this);


    }
    public void retriveData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                p=dataSnapshot.getValue(Prisoner.class);
                editIdprisoner.setText("Id: "+p.prisonerId);
                editprisonName.setText("Prison: "+p.namePrison);
                editDepartment.setText("Department: "+p.department);
                String s="Num cell: "+p.cellnum;
                editcellnum.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        if (save==v) {
            myRef = database.getReference("prisoners/"+p.getKey());

            p.namePrison = editprisonName.getText().toString();
            p.department = editDepartment.getText().toString();
            p.cellnum = Integer.parseInt(editcellnum.getText().toString());
            myRef.child("namePrison").setValue(p.namePrison);
            myRef.child("cellnum").setValue(p.cellnum);
            myRef.child("department").setValue(p.department);
            Toast.makeText(this, "Save success", Toast.LENGTH_SHORT).show();
            finish();
        }

        if(resetmeet==v){
            Schedule schedule=new Schedule();
            ArrayList<Meeting> sun=new ArrayList<>();
            ArrayList<Meeting> mon=new ArrayList<>();
            ArrayList<Meeting> tue=new ArrayList<>();
            ArrayList<Meeting> wed=new ArrayList<>();
            ArrayList<Meeting> thu=new ArrayList<>();
            ArrayList<Meeting> fri=new ArrayList<>();
            ArrayList<Meeting> sat=new ArrayList<>();
           for (int i=0;i<p.meetings.sun.size();i++){
                sun.add(new Meeting(p.meetings.sun.get(i).day,p.meetings.sun.get(i).startHour,p.meetings.sun.get(i).finisgHour,false,0,0));
            }
            for (int i=0;i<p.meetings.mon.size();i++){
               mon.add(new Meeting(p.meetings.mon.get(i).day,p.meetings.mon.get(i).startHour,p.meetings.mon.get(i).finisgHour,false,0,0));
            }
            for (int i=0;i<p.meetings.tue.size();i++){
                tue.add(new Meeting(p.meetings.tue.get(i).day,p.meetings.tue.get(i).startHour,p.meetings.tue.get(i).finisgHour,false,0,0));
            }
            for (int i=0;i<p.meetings.wed.size();i++){
                wed.add(new Meeting(p.meetings.wed.get(i).day,p.meetings.wed.get(i).startHour,p.meetings.wed.get(i).finisgHour,false,0,0));
            }
            for (int i=0;i<p.meetings.thu.size();i++){
                thu.add(new Meeting(p.meetings.thu.get(i).day,p.meetings.thu.get(i).startHour,p.meetings.thu.get(i).finisgHour,false,0,0));
            }
            for (int i=0;i<p.meetings.fri.size();i++){
                fri.add(new Meeting(p.meetings.fri.get(i).day,p.meetings.fri.get(i).startHour,p.meetings.fri.get(i).finisgHour,false,0,0));
            }
            for (int i=0;i<p.meetings.sat.size();i++){
                sat.add(new Meeting(p.meetings.sat.get(i).day,p.meetings.sat.get(i).startHour,p.meetings.sat.get(i).finisgHour,false,0,0));
            }
            schedule=new Schedule(sun,mon,tue,wed,thu,fri,sat);
           myRef.child("meetings").setValue(schedule);





        }
        if(editmeeting==v){
            Intent intent=new Intent(EditPrisonerActivity.this,Editprisonermeeting.class);
            intent.putExtra("key",key);
            startActivity(intent);
        }
        if(editvisitors==v){
            Intent intent=new Intent(EditPrisonerActivity.this,EditVisitorList.class);
            intent.putExtra("key",key);
            startActivity(intent);
        }




    }
}
