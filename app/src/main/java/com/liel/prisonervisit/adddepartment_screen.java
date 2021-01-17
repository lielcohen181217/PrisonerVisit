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

import static android.app.PendingIntent.getActivity;

public class adddepartment_screen extends AppCompatActivity implements View.OnClickListener {

    Button btnSave2;
    EditText  etdname, ettype, etnumcells;

    FirebaseDatabase database;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddepartment_screen);

        etnumcells = (EditText) findViewById(R.id.etnumcells);
        etnumcells.setOnClickListener(this);

        ettype = (EditText) findViewById(R.id.ettype);
        ettype.setOnClickListener(this);

        etdname = (EditText) findViewById(R.id.etdname);
        etdname.setOnClickListener(this);

        btnSave2 = (Button) findViewById(R.id.btnSave2);
        btnSave2.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        etdname.getText().toString();
        ettype.getText().toString();
        if (btnSave2 == v) {
            department d = new department(etdname.getText().toString(),ettype.getText().toString(),etnumcells.getText().toString());
            Intent intent = new Intent(this, departmentlist_screen.class);
            intent.putExtra("department",d);
            setResult(RESULT_OK, intent);

            finish();


        }


    }

}
