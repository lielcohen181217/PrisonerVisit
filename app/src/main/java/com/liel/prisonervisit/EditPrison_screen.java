package com.liel.prisonervisit;

import android.content.Intent;
import android.location.Address;
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

public class EditPrison_screen extends AppCompatActivity implements View.OnClickListener {
    Button btneditdepartment;
    Button save4;
    FirebaseDatabase database;
    DatabaseReference prisonRef;
    String key;
    prison p;
    EditText pname1,address1;
    ArrayList<department> newdepartments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prison_screen);

        btneditdepartment=(Button)findViewById(R.id.btndepartments1);
        btneditdepartment.setOnClickListener(this);
        save4=(Button)findViewById(R.id.btnSave4);
        save4.setOnClickListener(this);
        pname1 = (EditText) findViewById(R.id.etpname1);
        address1 = (EditText) findViewById(R.id.etaddress1);
        pname1.setOnClickListener(this);
        address1.setOnClickListener(this);


        Intent intent=getIntent();
        database = FirebaseDatabase.getInstance();
        key = intent.getExtras().getString("key");
        prisonRef = database.getReference("prisons/"+key);
        this.retrieveData();


    }

    public void retrieveData(){

        prisonRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                p = dataSnapshot.getValue(prison.class);
                pname1.setText(p.getName());
                address1.setText(p.getAddress());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }





    @Override
    public void onClick(View v) {



        if(btneditdepartment==v)

        {
            Intent intent = new Intent(this, EditDepartments_screen.class);
            if(p.getDepartments()==null)p.setDepartments(new ArrayList<department>());
            intent.putExtra("departments",p.getDepartments());
            startActivityForResult(intent, 0);



        }
        if(save4==v){
            prisonRef = database.getReference("prisons/"+p.getKey());
            DatabaseReference prisonsRef = FirebaseDatabase.getInstance().getReference();

            p.setName(pname1.getText().toString());
            p.setAddress(address1.getText().toString());
            if(newdepartments!=null)
               p.setDepartments(newdepartments);

            prisonRef.setValue(p);

            finish();

        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0)//return from test activity

        {

            if(resultCode==RESULT_OK)

            {

                newdepartments =(ArrayList<department>)data.getExtras().getSerializable("newdepartments");


            }

            else if(requestCode==RESULT_CANCELED)

            {

                Toast.makeText(this,"user cancelled test",Toast.LENGTH_LONG).show();

            }

        }

    }

}
