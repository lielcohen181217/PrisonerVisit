package com.liel.prisonervisit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ScreenListofvisitors extends AppCompatActivity {
ListView lv;
ArrayList<Integer> visitorsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_listofvisitors);

        lv=findViewById(R.id.visitorsItem);
        visitorsList=(ArrayList<Integer>)getIntent().getExtras().getSerializable("visitors");
        ArrayAdapter<Integer> itemAdapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,visitorsList);
        lv.setAdapter(itemAdapter);


    }
}
