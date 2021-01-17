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




public class AddPrisoner extends AppCompatActivity implements View.OnClickListener {
     Button addPrisoner;
     Button editListVisitorId;
     Button editSchedules;
     EditText prisonName;
     EditText department;
     EditText cellnum;
     EditText prisonerId;
     EditText Status;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Schedule s;
    ArrayList<Integer> visitorId;
    ArrayList<Meeting> sun=new ArrayList<>();
    ArrayList<Meeting> mon=new ArrayList<>();
    ArrayList<Meeting> tue=new ArrayList<>();
    ArrayList<Meeting> wed=new ArrayList<>();
    ArrayList<Meeting> thu=new ArrayList<>();
    ArrayList<Meeting> fri=new ArrayList<>();
    ArrayList<Meeting> sat=new ArrayList<>();
    ArrayList<String> listId=new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prisoner);

        database = FirebaseDatabase.getInstance();



        addPrisoner=findViewById(R.id.addtheprisoner);
        editListVisitorId=findViewById(R.id.editListvisitorId);
        editSchedules=findViewById(R.id.editSchedule);
        prisonName=findViewById(R.id.prisoname);
        department=findViewById(R.id.departmentname);
        cellnum=findViewById(R.id.cellnum);
        prisonerId=findViewById(R.id.prisonerId);
        Status=findViewById(R.id.status);

        listId=listID();

        addPrisoner.setOnClickListener((View.OnClickListener)this);
        editListVisitorId.setOnClickListener((View.OnClickListener)this);
        editSchedules.setOnClickListener((View.OnClickListener)this);

      sun.add(new Meeting("sun",12,13,false,0,0));
      mon.add(new Meeting("mon",12,13,false,0,0));
              tue.add(new Meeting("tue",12,13,false,0,0));
                      wed.add(new Meeting("wed",12,13,false,0,0));
                              thu.add(new Meeting("thu",12,13,false,0,0));
                                      fri.add(new Meeting("fri",12,13,false,0,0));
                                              sat.add(new Meeting("sat",12,13,false,0,0)) ;
              s=new Schedule(sun,mon,tue,wed,thu,fri,sat);

    }
    public void onClick(View v) {


        if(addPrisoner==v){
            String  pn= prisonName.getText().toString();
            String  d=department.getText().toString();
            int cn=Integer.parseInt(cellnum.getText().toString());
            String pId=prisonerId.getText().toString();
            String st=Status.getText().toString();
            Prisoner p=new Prisoner("",pn,d,cn,visitorId,pId,st,s);
            if(!listId.contains(pId)) {
                myRef = database.getReference("prisoners").push();
                p.setKey(myRef.getKey());
                myRef.setValue(p);

                Toast.makeText(this, "The your adding is success", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Toast.makeText(this, "The id already exist", Toast.LENGTH_SHORT).show();
            }
        }
        if (editSchedules == v) {
              Intent intent=new Intent(AddPrisoner.this,AddScheduleMeetin.class);
            startActivityForResult(intent, 1);


        }
        if(editListVisitorId==v){
            Intent intent = new Intent(this, AddVisitorIdlist.class);
            startActivityForResult(intent, 0);

        }





    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0)//return from test activity

        {

            if(resultCode==RESULT_OK)

            {



               ListVisitorId l=(ListVisitorId)data.getExtras().getSerializable("visitorlist");
               visitorId=l.getListV();


            }


            else if(requestCode==RESULT_CANCELED)

            {

                Toast.makeText(this,"user cancelled test",Toast.LENGTH_LONG).show();

            }

        }
        if(requestCode==1){
              if(resultCode==RESULT_OK) {
          Schedule        st =new Schedule ((Schedule) data.getExtras().getSerializable("meeting"));
          for (int i=0;i<st.sun.size();i++){
              sun.add(st.sun.get(i));
          }
                  for (int i=0;i<st.mon.size();i++){
                      mon.add(st.mon.get(i));
                  }
                  for (int i=0;i<st.tue.size();i++){
                      tue.add(st.tue.get(i));
                  }
                  for (int i=0;i<st.wed.size();i++){
                      wed.add(st.wed.get(i));
                  }
                  for (int i=0;i<st.thu.size();i++){
                      thu.add(st.thu.get(i));
                  }
                  for (int i=0;i<st.fri.size();i++){
                      fri.add(st.fri.get(i));
                  }
                  for (int i=0;i<st.sat.size();i++){
                      sat.add(st.sat.get(i));
                  }


              }



        }


    }
    public ArrayList<String> listID(){
        final ArrayList<String> listid=new ArrayList<>();
        myRef=database.getReference("prisoners");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    Prisoner p=data.getValue(Prisoner.class);
                    listid.add(p.getPrisonerId());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return listid;
    }



}
