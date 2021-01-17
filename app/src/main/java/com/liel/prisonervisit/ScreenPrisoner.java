package com.liel.prisonervisit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScreenPrisoner extends AppCompatActivity {
ListView lv;
ArrayList<Prisoner> Prisoners;
Prisoner p;



   AllPrisonerAdapter allPrisonerAdapter;
   private DatabaseReference database;
   DatabaseReference current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_prisoner);
        database=FirebaseDatabase.getInstance().getReference("prisoners");
          lv=(ListView)findViewById(R.id.tv);
          this.retriveData();
          lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Prisoner p=Prisoners.get(position);
                  Intent intent=new Intent(ScreenPrisoner.this,EditPrisonerActivity.class);
                  intent.putExtra("key",p.getKey());
                  startActivity(intent);
              }
          });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                p = Prisoners.get(position);
                current = FirebaseDatabase.getInstance().getReference("prisoners/"+p.getKey());


                AlertDialog.Builder builder = new AlertDialog.Builder(ScreenPrisoner.this);
                builder.setTitle("Warning!");
                builder.setMessage("You about to delete this item!");

                builder.setCancelable(true);
                builder.setPositiveButton("Delete", new ScreenPrisoner.HandleAlertDialogListener());
                builder.setNegativeButton("Cancel", new ScreenPrisoner.HandleAlertDialogListener());
                AlertDialog dialog=builder.create();
                dialog.show();
                return true;
            }
        });



    }


    public void retriveData(){
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Prisoners=new ArrayList<Prisoner>();
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    Prisoner p=data.getValue(Prisoner.class);
                    Prisoners.add(p);
                }
                allPrisonerAdapter =new AllPrisonerAdapter(ScreenPrisoner.this,0,0,Prisoners);
                   lv.setAdapter(allPrisonerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public  class  HandleAlertDialogListener implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int which) {

            if(which==-1)   {
                current.removeValue();
            }
            if(which==-2){

            }



        }
    }
}
