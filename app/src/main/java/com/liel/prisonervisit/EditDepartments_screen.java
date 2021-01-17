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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditDepartments_screen extends AppCompatActivity implements View.OnClickListener {

    ListView lv;
    ArrayList<department> olddepartments;
    AlldepartmentsAdapter alldepartmentsAdapter;
    DatabaseReference database;
    Button adddepar1,btnsave8;
    String key;
    department d ;
    department d1;
    //ArrayList<department> newdepartments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_departments_screen);
        adddepar1 = (Button)findViewById(R.id.adddepar1);
        adddepar1.setOnClickListener(this);
        btnsave8 = (Button)findViewById(R.id.btnsave8);
        btnsave8.setOnClickListener(this);
        Intent intent = getIntent();
        olddepartments = (ArrayList<department>)intent.getExtras().getSerializable("departments");
        lv=(ListView)findViewById(R.id.lv);
        alldepartmentsAdapter = new AlldepartmentsAdapter(this,0,0,olddepartments);
        lv.setAdapter(alldepartmentsAdapter);
        d = (department)intent.getExtras().getSerializable("department");


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                department d = olddepartments.get(position);
                Intent intent = new Intent(EditDepartments_screen.this,EditDepartment_screen.class);
                intent.putExtra("department",d);
                intent.putExtra("index",position);
                //intent.putExtra("key",key);
                startActivityForResult(intent, 0);

            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                d1 = olddepartments.get(position);


                AlertDialog.Builder builder = new AlertDialog.Builder(EditDepartments_screen.this);
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
                olddepartments.remove(d1);
                alldepartmentsAdapter.notifyDataSetChanged();
            }
            if(which==-2){

            }



        }
    }


    @Override
    public void onClick(View v) {

        if(v==adddepar1){

            Intent intent = new Intent(this,adddepartment_screen.class);
            startActivityForResult(intent, 1);

        }

        if(btnsave8 == v){

            Intent intent = new Intent(this, EditPrison_screen.class);
            intent.putExtra("newdepartments",olddepartments);
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

                int index = data.getExtras().getInt("index");
                department d = (department) data.getExtras().getSerializable("department");
                olddepartments.set(index,d);
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

                department d = (department) data.getExtras().getSerializable("department");
                olddepartments.add(d);
                alldepartmentsAdapter.notifyDataSetChanged();
            }

            else if(requestCode==RESULT_CANCELED)

            {

                Toast.makeText(this,"user cancelled test",Toast.LENGTH_LONG).show();

            }

        }


    }

}
