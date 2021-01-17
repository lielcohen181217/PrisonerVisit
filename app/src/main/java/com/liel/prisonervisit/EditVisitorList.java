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

public class EditVisitorList extends AppCompatActivity implements View.OnClickListener {

    EditText visitorid;
    Button add;
    Button viewvisitors;

    DatabaseReference myRef;

    FirebaseDatabase database;

    String key;
    ArrayList<Integer> visitors=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_visitor_list);
        database=FirebaseDatabase.getInstance();
        key=(String)getIntent().getExtras().getString("key");
        myRef=database.getReference("prisoners/"+key);
        visitorid=findViewById(R.id.EditVisitorid);
        add=findViewById(R.id.AddNewVisitor);
        viewvisitors=findViewById(R.id.ViewVisitors);
        this.retrivedata();
        add.setOnClickListener(this);
        viewvisitors.setOnClickListener(this);

    }
    public void retrivedata(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Prisoner p=dataSnapshot.getValue(Prisoner.class);
                if(p.visitorsId.size()>0) {
                    for (int i = 0; i < p.visitorsId.size(); i++) {
                        visitors.add(p.visitorsId.get(i));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if(add==v){
            int vis=Integer.parseInt(visitorid.getText().toString());
            visitors.add(vis);
            myRef.child("visitorsId").setValue(visitors);
            Toast.makeText(this, "It's added", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(viewvisitors==v){
            Intent intent =new Intent(EditVisitorList.this,ScreenListofvisitors.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("visitors",visitors);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}
