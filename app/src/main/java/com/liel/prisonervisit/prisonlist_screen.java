package com.liel.prisonervisit;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class prisonlist_screen extends AppCompatActivity implements View.OnClickListener {

    Button addp;
    ListView lv;
    ArrayList<prison> prisons;
    AllprisonsAdapter allprisonsAdapter;
    DatabaseReference database;
    prison p;
    DatabaseReference current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisonlist_screen);


        database = FirebaseDatabase.getInstance().getReference("prisons");
        addp=(Button)findViewById(R.id.addprison);
        addp.setOnClickListener(this);
        lv=(ListView)findViewById(R.id.lv);
        this.retrieveData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prison p1 = prisons.get(position);
                Intent intent = new Intent(prisonlist_screen.this,EditPrison_screen.class);
                intent.putExtra("key",p1.getKey());
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                p = prisons.get(position);
                current = FirebaseDatabase.getInstance().getReference("prisons/"+p.getKey());


                AlertDialog.Builder builder = new AlertDialog.Builder(prisonlist_screen.this);
                builder.setTitle("Warning!");
                builder.setMessage("You about to delete this item!");

                builder.setCancelable(true);
                builder.setPositiveButton("Delete", new HandleAlertDialogListener());
                builder.setNegativeButton("Cancel", new HandleAlertDialogListener());
                AlertDialog dialog=builder.create();
                dialog.show();
                return true;
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

    public void retrieveData() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                prisons = new ArrayList<prison>();
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    prison p = data.getValue(prison.class);
                    prisons.add(p);
                }
                allprisonsAdapter = new AllprisonsAdapter(prisonlist_screen.this,0,0,prisons);
                lv.setAdapter(allprisonsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }



    public void onClick(View v) {

        if (addp == v)

        {

            Intent intent = new Intent(this,addprison_screen.class);
            startActivity(intent);

        }
    }
}