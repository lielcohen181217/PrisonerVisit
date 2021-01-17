package com.liel.prisonervisit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    Button btvisitor;
    Button btemployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btvisitor=(Button)findViewById(R.id.btv);
        btemployee=(Button)findViewById(R.id.bte);


        btvisitor.setOnClickListener((View.OnClickListener) this);
        btemployee.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (btvisitor==v){
            Intent intent =new Intent(this,Enter_Visitor.class);
            startActivity(intent);
        }

if(btemployee==v){
            Intent intent=new Intent(this,Enter_Employee.class);
            startActivity(intent);
}




    }
}
