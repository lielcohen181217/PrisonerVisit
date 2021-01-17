package com.liel.prisonervisit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Enter_Employee extends AppCompatActivity implements View.OnClickListener {
Button log_in;
EditText user_name;
EditText password;
    DatabaseReference myRef;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter__employee);
 database = FirebaseDatabase.getInstance();
     myRef = database.getReference("employes") ;
        log_in=findViewById(R.id.login);
        user_name=findViewById(R.id.username);
        password=findViewById(R.id.password);


        log_in.setOnClickListener(this);
        myRef.child("liel").setValue(new Employe("liel","123456","a"));
        myRef.child("rita").setValue(new Employe("rita","123456","a"));
        myRef.child("dani").setValue(new Employe("dani","123456","a"));
        System.out.println("fsdgrhehywqfrwqwagregesfgesgesgsrhrgh"+user_name.getText());

    }



public void StartActivityMenage(){
        Intent intent =new Intent(this,Menu_maneger.class);
        startActivity(intent);
    }
public void  StartActivityWarden(String key){
Intent intent=new Intent(this,ScreenWarder.class);

intent.putExtra("wardenid",key);

startActivity(intent);
}





    private void signIn(final String User_Name, final String PassWord){
        final ValueEventListener wrong = myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                   if (dataSnapshot.child(User_Name).exists()) {
                       Employe user = dataSnapshot.child(User_Name).getValue(Employe.class);

                       if (user.getPassword().equals(PassWord) && user.getUsername().equals(User_Name)) {
                           if (user.getRank().equals("a")) {
                               CharSequence value = "Hello " + User_Name;
                               Toast.makeText(Enter_Employee.this, value, Toast.LENGTH_SHORT).show();
                               StartActivityMenage();
                           } else {
                               CharSequence value = "Hello " + User_Name;
                               Toast.makeText(Enter_Employee.this, value, Toast.LENGTH_SHORT).show();
                               System.out.println(user.getKey());
                               StartActivityWarden(user.getKey());

                           }
                       } else {
                           Toast.makeText(Enter_Employee.this, "uncorrect password ", Toast.LENGTH_LONG).show();
                       }
                   }

                else{
                    Toast.makeText(Enter_Employee.this,"Not exist",Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    public void onClick(View v) {


        signIn(user_name.getText().toString(), password.getText().toString());

    }

}
