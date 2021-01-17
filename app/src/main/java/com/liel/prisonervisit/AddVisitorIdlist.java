package com.liel.prisonervisit;

import android.content.Intent;
import android.os.Parcelable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class AddVisitorIdlist extends AppCompatActivity implements View.OnClickListener {


    Button addVisitor;
    Button saveVisitor;
    EditText visitorId;

    ArrayList<Integer> visitidlist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visitor_idlist);
        addVisitor=findViewById(R.id.addVisitor);
        saveVisitor=findViewById(R.id.savesVisitors);
        visitorId=findViewById(R.id.visitorid);
        visitidlist=new ArrayList<>();
        addVisitor.setOnClickListener((View.OnClickListener)this);
        saveVisitor.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(addVisitor==v){
            int vId=Integer.parseInt(visitorId.getText().toString());
            visitidlist.add(vId);
            Toast.makeText(this, "It's added", Toast.LENGTH_SHORT).show();
            visitorId.setText("");
        }
        if (saveVisitor == v) {
            ListVisitorId   l =new ListVisitorId(visitidlist);
            Intent intent=new Intent (this,AddPrisoner.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("visitorlist",l);
          intent.putExtras(bundle);
          setResult(RESULT_OK,intent);

            Toast.makeText(this, "Saved success", Toast.LENGTH_SHORT).show();
          finish();



        }
    }
}
