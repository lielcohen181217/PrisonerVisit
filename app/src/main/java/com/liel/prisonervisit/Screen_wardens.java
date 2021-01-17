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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Screen_wardens extends AppCompatActivity {
    ListView lv;
    ArrayList<Warden> wardens;
    AllwardenAdapter allwardenAdapter;
    private DatabaseReference database;
    Warden w=new Warden();
    DatabaseReference current;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_wardens);

        database=FirebaseDatabase.getInstance().getReference("wardens");
        lv=findViewById(R.id.listwardens);
        this.retreivedata();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Warden w=wardens.get(position);
                Intent intent=new Intent(Screen_wardens.this,EditWardenActivity.class);

                intent.putExtra("keywarden",w.getKey());

                startActivity(intent);



            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                w = wardens.get(position);
                current = FirebaseDatabase.getInstance().getReference("wardens/"+w.getKey());


                AlertDialog.Builder builder = new AlertDialog.Builder(Screen_wardens.this);
                builder.setTitle("Warning!");
                builder.setMessage("You about to delete this item!");

                builder.setCancelable(true);
                builder.setPositiveButton("Delete", new Screen_wardens.HandleAlertDialogListener());
                builder.setNegativeButton("Cancel", new Screen_wardens.HandleAlertDialogListener());
                AlertDialog dialog=builder.create();
                dialog.show();
                return true;
            }
        });




    }



    public void retreivedata(){
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wardens=new ArrayList<Warden>();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Warden w=data.getValue(Warden.class);
                    wardens.add(w);
                }
                allwardenAdapter=new AllwardenAdapter(Screen_wardens.this,0,0,wardens);
                lv.setAdapter(allwardenAdapter);

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
