package com.liel.prisonervisit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class addprison_screen extends AppCompatActivity implements View.OnClickListener {

    Button btsave,btdepartments;
    EditText pname,address;
    FirebaseDatabase database;
    DatabaseReference prisonRef;
    prison p;
    ArrayList<department> departments = new ArrayList<department>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprison_screen);

        btdepartments = (Button)findViewById(R.id.btndepartments);
        btsave = (Button)findViewById(R.id.btnSave1);
        pname = (EditText) findViewById(R.id.etpname);
        address = (EditText) findViewById(R.id.etaddress);

        btdepartments.setOnClickListener(this);
        btsave.setOnClickListener(this);
        pname.setOnClickListener(this);
        address.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();



    }

    @Override
    public void onClick(View v) {
        p = new prison("",pname.getText().toString(), address.getText().toString());
        if(p.getDepartments()==null)p.setDepartments(new ArrayList<department>());
        departments.add(new department());
        p.setDepartments(departments);

        if (v == btdepartments) {
            Intent intent = new Intent(this, departmentlist_screen.class);
            startActivityForResult(intent, 0);
        }
        if (v == btsave) {
            Intent intent = new Intent(this, prisonlist_screen.class);
            prisonRef = database.getReference("prisons").push();
            p.setKey(prisonRef.getKey());
            prisonRef.setValue(p);





            finish();


        }

    }
        @Override

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode==0)//return from test activity

            {

                if(resultCode==RESULT_OK)

                {

                    departments =(ArrayList<department>)data.getExtras().getSerializable("departments");

                    p.setDepartments(departments);


                }

                else if(requestCode==RESULT_CANCELED)

                {

                    Toast.makeText(this,"user cancelled test",Toast.LENGTH_LONG).show();

                }

            }

        }






}
