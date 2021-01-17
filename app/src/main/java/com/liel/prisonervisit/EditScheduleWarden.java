package com.liel.prisonervisit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EditScheduleWarden extends AppCompatActivity implements View.OnClickListener {
    Button addSchedule;
    Button saveSchedules;
    EditText theday;
    EditText Starthour;
    EditText Endhour;

    ArrayList<Time> t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule_warden);
        addSchedule=findViewById(R.id.addTheschedulesWArden);
        saveSchedules=findViewById(R.id.savescheduleswarden);
        theday=findViewById(R.id.daywarden);
        Starthour=findViewById(R.id.starthourWarden);
        Endhour=findViewById(R.id.endhourWarden);
        addSchedule.setOnClickListener(this);
        saveSchedules.setOnClickListener(this);
        t=new ArrayList<>();






    }

    @Override
    public void onClick(View v) {

        if(addSchedule==v){
            String day=theday.getText().toString();
            int s=Integer.parseInt(Starthour.getText().toString());
            int e=Integer.parseInt(Endhour.getText().toString()) ;
            Time time=new Time(s,e,day);
            t.add(time);
            Toast.makeText(this, "It's added", Toast.LENGTH_SHORT).show();

        }
       if(saveSchedules==v){
           Intent intent =new Intent(this,AddWarden.class);
           Bundle bundle=new Bundle();
           bundle.putSerializable("schedulewarden",t);
           intent.putExtras(bundle);
           setResult(RESULT_OK,intent);

           Toast.makeText(this, "Saved success", Toast.LENGTH_SHORT).show();
finish();



       }




    }
}
