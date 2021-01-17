package com.liel.prisonervisit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class departmentlist_screen extends AppCompatActivity implements View.OnClickListener {

    Button adddepar,btnsave3;
    String pkey;
    FirebaseDatabase database;
    DatabaseReference departmentRef;
    ArrayList<department> departments = new ArrayList<department>();
    department d;
    department d1;

    ListView lv;
    AlldepartmentsAdapter alldepartmentsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departmentlist_screen);

        adddepar=(Button)findViewById(R.id.adddepar);
        adddepar.setOnClickListener(this);
        btnsave3=(Button)findViewById(R.id.btnsave3);
        btnsave3.setOnClickListener(this);
        lv=(ListView)findViewById(R.id.lv);
        alldepartmentsAdapter = new AlldepartmentsAdapter(this,0,0,departments);
        lv.setAdapter(alldepartmentsAdapter);
        Intent intent = getIntent();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                department d = departments.get(position);
                Intent intent = new Intent(departmentlist_screen.this,EditDepartment_screen.class);
                intent.putExtra("department",d);
                intent.putExtra("index",position);
                //intent.putExtra("key",key);
                startActivityForResult(intent, 1);

            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                d1 = departments.get(position);


                AlertDialog.Builder builder = new AlertDialog.Builder(departmentlist_screen.this);
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
             departments.remove(d1);
             alldepartmentsAdapter.notifyDataSetChanged();
         }
         if(which==-2){

         }



        }
    }


    public void onClick(View v) {

        if (adddepar == v)

        {

            Intent intent = new Intent(this, adddepartment_screen.class);
            startActivityForResult(intent, 0);

        }

        if(btnsave3 == v){

            Intent intent = new Intent(this, addprison_screen.class);
            intent.putExtra("departments",departments);
            setResult(RESULT_OK, intent);
            finish();

        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0)//return from test activity

        {

            if(resultCode==RESULT_OK)

            {
                d = (department)data.getExtras().getSerializable("department");
                departments.add(d);
                alldepartmentsAdapter.notifyDataSetChanged();
            }

            else if(requestCode==RESULT_CANCELED)

            {

                Toast.makeText(this,"user cancelled test",Toast.LENGTH_LONG).show();

            }

        }
        if(requestCode==1)//return from test activity

        {

            if(resultCode==RESULT_OK)

            {

                int index = data.getExtras().getInt("index");
                department d = (department) data.getExtras().getSerializable("department");
                departments.set(index,d);
                alldepartmentsAdapter.notifyDataSetChanged();
            }

            else if(requestCode==RESULT_CANCELED)

            {

                Toast.makeText(this,"user cancelled test",Toast.LENGTH_LONG).show();

            }

        }

    }



}

