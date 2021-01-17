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

public class AddScheduleMeetin extends AppCompatActivity implements View.OnClickListener {
    EditText day;
    EditText starthour;
    EditText endhour;
    Button addSchedule;
    Button save;
    Schedule schedule;
    ArrayList<Meeting> sun;
    ArrayList<Meeting>mon;
    ArrayList<Meeting>tue;
    ArrayList<Meeting>wed;
    ArrayList<Meeting>thu;
    ArrayList<Meeting>fri;
    ArrayList<Meeting>sat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule_meetin);

        day = findViewById(R.id.day);
        starthour = findViewById(R.id.starthour);
        endhour = findViewById(R.id.endhour);
        addSchedule = findViewById(R.id.addTheschedules);


        addSchedule.setOnClickListener(this);


      ArrayList  <String> days =new ArrayList<>();
      days.add("sun");
        days.add("mon");
        days.add("tue");
        days.add("wed");
        days.add("thu");
        days.add("fri");
        days.add("sat");
      schedule=new Schedule();
     sun=new ArrayList<>();
mon=new ArrayList<>();
   tue=new ArrayList<>();
        wed=new ArrayList<>();
       thu=new ArrayList<>();
        fri=new ArrayList<>();
      sat=new ArrayList<>();

        save=findViewById(R.id.savetheschedule);
        save.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {


        if (addSchedule == v) {
            String d = day.getText().toString();
            int start = Integer.parseInt(starthour.getText().toString());
            int end = Integer.parseInt(endhour.getText().toString());
            System.out.println(start);
            if (start > end || start > 24 || end > 24) {
                Toast.makeText(AddScheduleMeetin.this, "Start hour or end hour uncorrect ", Toast.LENGTH_SHORT).show();
            } else {
                ArrayList  <String> days =new ArrayList<>();
                days.add("sun");
                days.add("mon");
                days.add("tue");
                days.add("wed");
                days.add("thu");
                days.add("fri");
                days.add("sat");
                 if(days.contains(d)){

                        Meeting m = new Meeting(d, start, end, false, 0, 0);
                        if(d.equals("sun"))sun.add(m);
                     if(d.equals("mon"))mon.add(m);
                     if(d.equals("tue"))tue.add(m);
                     if(d.equals("wed"))wed.add(m);
                     if(d.equals("thu"))thu.add(m);
                     if(d.equals("fri"))fri.add(m);
                     if(d.equals("sat"))sat.add(m);

                     Toast.makeText(this, "Is added", Toast.LENGTH_SHORT).show();
                     day.setText("");
                     starthour.setText("");
                     endhour.setText("");


                    }
               else {
                     Toast.makeText(this, "The type day is not legal", Toast.LENGTH_SHORT).show();
                 }

            }
        }
        if(save==v){
            Intent intent = new Intent(this, AddPrisoner.class);
         schedule=new Schedule(sun,mon,tue,wed,thu,fri,sat);
            Bundle bundle = new Bundle();
            bundle.putSerializable("meeting", schedule);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);

            Toast.makeText(this, "Added success", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
