package com.liel.prisonervisit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import  android.content.Intent;


public class Menu_maneger extends AppCompatActivity implements View.OnClickListener {
    Button prisoner;
    Button warden;
    Button prisons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_maneger);



        prisoner=findViewById(R.id.prisoners);
        warden=findViewById(R.id.warden);
        prisoner.setOnClickListener(this);
        warden.setOnClickListener(this);
        prisons=findViewById(R.id.prison);
        prisons.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(prisoner==v){
            Intent intent =new Intent(this,MenuPrisoners.class);
            startActivity(intent);
        }
        if(warden==v){
            Intent intent=new Intent(this,MenuWarden.class);
           startActivity(intent);

        }
        if(prisons==v){
            Intent intent=new Intent(this,prisonlist_screen.class);
            startActivity(intent);

        }
    }
}
