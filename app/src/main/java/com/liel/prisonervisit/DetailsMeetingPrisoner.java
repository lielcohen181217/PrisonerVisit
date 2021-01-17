package com.liel.prisonervisit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsMeetingPrisoner extends AppCompatActivity {
     TextView start;
     TextView end;
     TextView occupied;
     TextView prisonerId;
     TextView VisitorId;
     Meeting m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_meeting_prisoner);
        start=findViewById(R.id.beginning);
        end=findViewById(R.id.ending);
        occupied=findViewById(R.id.occupied);
        prisonerId=findViewById(R.id.Numprisoner);
        VisitorId=findViewById(R.id.Numvisitor);
        m=new Meeting();
        m=(Meeting)getIntent().getExtras().getSerializable("meeting");
        start.setText("Start: "+m.startHour+":00");
        end.setText("End: "+m.finisgHour+":00");
        occupied.setText("Occupied: "+m.occupied);
       if(m.IDofprisoner!=0&&m.IDofVisitor!=0){
           prisonerId.setText("Prisoner: "+m.IDofprisoner);
           VisitorId.setText("Visitor: "+m.IDofVisitor);
       }

    }
}
