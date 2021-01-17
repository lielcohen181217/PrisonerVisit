package com.liel.prisonervisit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuPrisoners extends AppCompatActivity implements View.OnClickListener {


    DatabaseReference myRef;
    FirebaseDatabase database;
    Button viewPrisoner;
    Button EnterAddPrisoner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_prisoners);
        database = FirebaseDatabase.getInstance();




       viewPrisoner=(Button)findViewById(R.id.viewprisoners);
       EnterAddPrisoner=(Button)findViewById(R.id.addprisoner);

       viewPrisoner.setOnClickListener((View.OnClickListener) this);
       EnterAddPrisoner.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

            if (viewPrisoner == v) {
                Intent intent = new Intent(MenuPrisoners.this, ScreenPrisoner.class);
                startActivity(intent);
            }
            if (EnterAddPrisoner == v) {
                Intent intent = new Intent(MenuPrisoners.this, AddPrisoner.class);
                startActivity(intent);
            }




            }
        }


