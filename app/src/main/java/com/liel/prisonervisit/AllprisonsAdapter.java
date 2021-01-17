package com.liel.prisonervisit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AllprisonsAdapter extends ArrayAdapter<prison> {

    Context context;
    List<prison> objects;


    public AllprisonsAdapter(Context context, int resource, int textViewResourceId, ArrayList<prison> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.objects = objects;
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_layout,parent,false);

        TextView tvTitle = (TextView)view.findViewById(R.id.tvname);
        prison temp = objects.get(position);
        tvTitle.setText(temp.getName());
        return view;

    }
}

