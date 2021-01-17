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

public class AddWarden extends AppCompatActivity implements View.OnClickListener {
    Button addWarden;
    Button EditScheduleWarden;
    EditText UserName;
    EditText Password;

    EditText prisonname;
    EditText departmentName;
    ArrayList<Time> t;
    DatabaseReference myRef;
  
    FirebaseDatabase database;
    ArrayList<String> listId=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_warden);
        database = FirebaseDatabase.getInstance();
       ;



        addWarden=findViewById(R.id.addthewarden);
        EditScheduleWarden=findViewById(R.id.editschedulewarden);
        UserName=findViewById(R.id.usernamewarden);
        Password=findViewById(R.id.passwordwarden);

        prisonname=findViewById(R.id.Nameofprison);
        departmentName=findViewById(R.id.nameofdepartment);

       listId=listWardenId();

        addWarden.setOnClickListener(this);
        EditScheduleWarden.setOnClickListener(this);
        t=new ArrayList<>();




    }

    @Override
    public void onClick(View v) {


        if(addWarden==v){
         String u=UserName.getText().toString();
         String p=Password.getText().toString();

         String prisonN=prisonname.getText().toString();
         String departmentN=departmentName.getText().toString();



             Warden w=new Warden("",u,p,"w",prisonN,departmentN,t);
             if(!listId.contains(u)) {
                 myRef = database.getReference("wardens").push();
                 w.setKey(myRef.getKey());
                 myRef.setValue(w);
                 myRef = database.getReference("employes");
                 Employe e = new Employe(w.getKey(), u, p, "w");
                 myRef.child(u).setValue(e);


                 Toast.makeText(this, "The your adding is success", Toast.LENGTH_SHORT).show();
                 finish();
             }
             else{
                 Toast.makeText(this, "The user name warden is already exist", Toast.LENGTH_SHORT).show();
             }










        }
        if(EditScheduleWarden==v){
            Intent intent = new Intent(this, EditScheduleWarden.class);
            startActivityForResult(intent, 0);
        }


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0)//return from test activity

        {

            if(resultCode==RESULT_OK)

            {
             ArrayList<Time>  times=new ArrayList<>();
             times=(ArrayList<Time>)data.getExtras().getSerializable("schedulewarden");
             t=times;






            }


            else if(requestCode==RESULT_CANCELED)

            {

                Toast.makeText(this,"user cancelled test",Toast.LENGTH_LONG).show();

            }

        }



    }
    public ArrayList<String> listWardenId(){
        final ArrayList<String> wardens=new ArrayList<>();
        myRef=database.getReference("wardens");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Warden w=data.getValue(Warden.class);
                    wardens.add(w.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return wardens;
    }


}
