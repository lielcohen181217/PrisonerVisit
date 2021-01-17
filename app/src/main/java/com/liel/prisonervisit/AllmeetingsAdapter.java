package com.liel.prisonervisit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AllmeetingsAdapter extends ArrayAdapter {
    Context context;
    List<Meeting> objects;

    public AllmeetingsAdapter (Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.custom_meeting,parent,false);
        TextView tvtitle=(TextView)view.findViewById(R.id.daytitle);
        Meeting temp=objects.get(position);
        tvtitle.setText(temp.day);





        return view;
    }
}
