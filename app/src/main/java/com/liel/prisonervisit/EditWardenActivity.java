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

public class EditWardenActivity extends AppCompatActivity implements View.OnClickListener {
    EditText IdWarden;
    EditText Password;
    EditText editprisonName;
    EditText editdepartmentname;
    Button save;
    Button editschedule;
    String key;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Warden w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_warden);
        IdWarden=findViewById(R.id.EditIdWarden);
        Password=findViewById(R.id.Editpassword);
        editprisonName=findViewById(R.id.Editworkprison);
        editdepartmentname=findViewById(R.id.Editworkdepartment);
        save=findViewById(R.id.savethewarden);
        w=new Warden();
        save.setOnClickListener(this);
        database=FirebaseDatabase.getInstance();


        key=(String)getIntent().getExtras().getString("keywarden");
        System.out.println(key);
        myRef=database.getReference("wardens/"+key);
        this.retrivedata();
        editschedule=findViewById(R.id.editlistdaywarden);
        editschedule.setOnClickListener(this);






    }
    public void retrivedata(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 w=dataSnapshot.getValue(Warden.class);
                IdWarden.setText("Id: "+w.username);
                Password.setText("Password: "+w.password);
                editprisonName.setText("Prison: "+w.prisonname);
                editdepartmentname.setText("Department :"+w.department);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if (save == v) {
            myRef = database.getReference("wardens/" + w.getKey());

            w.username = IdWarden.getText().toString();
            w.password = Password.getText().toString();
            w.prisonname = editprisonName.getText().toString();
            w.department = editdepartmentname.getText().toString();
            myRef.child("prisonname").setValue(w.prisonname);
            myRef.child("password").setValue(w.password);
            myRef.child("department").setValue(w.department);
            myRef.child("username").setValue(w.username);
            Employe e = new Employe(w.getKey(), w.username, w.password, "w");
            myRef = database.getReference("employes");
            myRef.child(w.username).setValue(e);

            Toast.makeText(this, "Save success", Toast.LENGTH_SHORT).show();
            finish();


        }
        if(editschedule==v){
            Intent intent =new Intent(EditWardenActivity.this,EditListDaysWardens.class);
            intent.putExtra("key",key);
            startActivity(intent);
        }
    }




}
