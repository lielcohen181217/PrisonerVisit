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

public class ScreenWarder extends AppCompatActivity {
    ListView lv;
    AlltimesAdapter alltimesAdapter;
    ArrayList<Time> times;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_warder);
        String key=(String) getIntent().getExtras().getString("wardenid");
        System.out.println(key);
        database=FirebaseDatabase.getInstance().getReference("wardens/"+key);
         lv=findViewById(R.id.viewmydays);
        times=new ArrayList<>();
        this.retriveData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Time t=times.get(position);
                Intent intent=new Intent(ScreenWarder.this,ViewDayHourWarden.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("time",t);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });




    }
    public void retriveData(){
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.child("schedule").getChildren()){
                    Time t=data.getValue(Time.class);
                    times.add(t);

                }
                alltimesAdapter=new AlltimesAdapter(ScreenWarder.this,0,0,times);
                lv.setAdapter(alltimesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
