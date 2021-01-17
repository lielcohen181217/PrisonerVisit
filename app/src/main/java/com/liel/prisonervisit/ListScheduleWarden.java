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

public class ListScheduleWarden extends AppCompatActivity {
ListView lv;
AlltimesAdapter alltimesAdapter;
String key;
ArrayList<Time> schedule=new ArrayList<>();
    DatabaseReference myRef;

    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_schedule_warden);
        database=FirebaseDatabase.getInstance();

        key=(String)getIntent().getExtras().getString("key");
        myRef=database.getReference("wardens/"+key);
        this.retrivedata();


        lv=findViewById(R.id.listOfitem);
        alltimesAdapter=new AlltimesAdapter(this,0,0,schedule);
        lv.setAdapter(alltimesAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Time t=schedule.get(position);
                Intent intent=new Intent(ListScheduleWarden.this,ViewDetailsWorkDay.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("time",t);
              intent.putExtras(bundle);
              startActivity(intent);


            }
        });




    }

    public void retrivedata(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Warden w = dataSnapshot.getValue(Warden.class);
                if (w.schedule.size() > 0) {
                    for (int i = 0; i < w.schedule.size(); i++) {
                        schedule.add(w.schedule.get(i));
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
