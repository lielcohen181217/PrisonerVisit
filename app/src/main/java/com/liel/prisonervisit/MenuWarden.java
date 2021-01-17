package com.liel.prisonervisit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuWarden extends AppCompatActivity implements View.OnClickListener {
    Button viewwarden;
    Button Addwarden;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_warden);


        viewwarden=findViewById(R.id.viewwardens);
        Addwarden=findViewById(R.id.addwarden);
        viewwarden.setOnClickListener(this);
        Addwarden.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(Addwarden==v){
            Intent  intent = new Intent(this,AddWarden.class);
            startActivity(intent);
        }
        if(viewwarden==v){
            Intent  intent = new Intent(this,Screen_wardens.class);
            startActivity(intent);
        }




    }
}
