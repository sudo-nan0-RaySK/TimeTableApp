package com.example.legendarychild.timetableapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by LegendaryChild on 09/12/17.
 */

public class ListOfFriends extends ArrayAdapter<Friend> {
    private Activity context;
    private List<Friend> frndList;


    public ListOfFriends(Activity context, List<Friend> frndList)
    {
        super(context,R.layout.free_not_free,frndList);
        this.context=context;
        this.frndList=frndList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator = context.getLayoutInflater();
        View listViewItem= inflator.inflate(R.layout.free_not_free,null,true);
        TextView txtName= (TextView)listViewItem.findViewById(R.id.txtName);
        TextView regTxt= (TextView)listViewItem.findViewById(R.id.regTxt);
        Friend friend= frndList.get(position);
        txtName.setText(friend.getName());
        regTxt.setText(friend.getRegNo());
        return listViewItem;
    }
}
