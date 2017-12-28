package com.example.legendarychild.timetableapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LegendaryChild on 09/12/17.
 */

public class ListOfSubjects extends ArrayAdapter<Period> {
    Activity context;
    List<Period> perList;
    public ListOfSubjects(Activity context,List<Period> perList)
    {
        super(context,R.layout.free_not_free,perList);
        this.context=context;
        this.perList=perList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator = context.getLayoutInflater();
        View listViewItem= inflator.inflate(R.layout.free_not_free,null,true);
        TextView txtName= (TextView)listViewItem.findViewById(R.id.txtName);
        TextView regTxt= (TextView)listViewItem.findViewById(R.id.regTxt);
        Period period= perList.get(position);
        txtName.setText(period.getNames());
        regTxt.setText("From "+period.getStartHours()+":"+period.getStartMins());
        return listViewItem;
    }
}
