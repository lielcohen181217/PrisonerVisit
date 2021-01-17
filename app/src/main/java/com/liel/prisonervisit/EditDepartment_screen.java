package com.liel.prisonervisit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditDepartment_screen extends AppCompatActivity implements View.OnClickListener {
    FirebaseDatabase database;
    DatabaseReference departmentRef;
    String key;
    int index;
    department d;
    EditText etdname1,ettype1,etnumcells1;
    Button btnSave4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_department_screen);

        etnumcells1 = (EditText) findViewById(R.id.etnumcells1);
        etnumcells1.setOnClickListener(this);

        ettype1 = (EditText) findViewById(R.id.ettype1);
        ettype1.setOnClickListener(this);

        etdname1 = (EditText) findViewById(R.id.etdname1);
        etdname1.setOnClickListener(this);

        btnSave4 = (Button) findViewById(R.id.btnSave4);
        btnSave4.setOnClickListener(this);


        Intent intent=getIntent();
        database = FirebaseDatabase.getInstance();
        //key = intent.getExtras().getString("key");
        index = intent.getExtras().getInt("index");
        //departmentRef = database.getReference("prisons/"+key+"/departments/"+index);
        d=(department)intent.getExtras().getSerializable("department");
        etdname1.setText(d.getName());
        ettype1.setText(d.getType());
        etnumcells1.setText(d.getNumOfCells());

    }


    @Override
    public void onClick(View v) {
        if(v==btnSave4){

            department d = new department(etdname1.getText().toString(),ettype1.getText().toString(),etnumcells1.getText().toString());
            Intent intent = new Intent(this, departmentlist_screen.class);
            intent.putExtra("department",d);
            intent.putExtra("index",index);
            setResult(RESULT_OK, intent);

            finish();



        }
    }
}
