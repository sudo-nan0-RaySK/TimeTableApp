package com.example.legendarychild.timetableapp;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class StatusActivity extends AppCompatActivity {
    TextView txtStatus;
    ListView subList;
    DatabaseReference subjRef;
    String currNode;
    List<Period> subjectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        txtStatus= (TextView)findViewById(R.id.txtStatus);
        subList=(ListView)findViewById(R.id.subList);
        Bundle bundle = getIntent().getExtras();
        currNode = bundle.getString("currNode");
        subjRef=subjRef = FirebaseDatabase.getInstance().getReference("timetable").child(currNode);
        subjectList=new ArrayList<>();

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateTextView();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        subjRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot periodSnapshot: dataSnapshot.getChildren())
                {
                    Period period=periodSnapshot.getValue(Period.class);
                    subjectList.add(period);
                }
                ListOfSubjects adapter = new ListOfSubjects(StatusActivity.this,
                        subjectList);
                subList.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public boolean isFree()
    {
        for(Period period: subjectList)
        {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC+5:30"));
            Date currentLocalTime = cal.getTime();
            DateFormat date = new SimpleDateFormat("HH:mm a");

            date.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));

            String localTime = date.format(currentLocalTime);
            int currHours= Integer.parseInt(localTime.substring(0,2));
            int currMins= Integer.parseInt(localTime.substring(3,5));

            int startHours=Integer.parseInt(period.getStartHours());
            int startMins= Integer.parseInt(period.getStartMins());

            int endHours=startHours;

            int endMins= startMins+50; //Assuming each period is 50 minutes long

            if(endMins>=60)
            {
                ++endHours;
                endMins=endMins-60;

            }

            int startTime=(startHours*100)+startMins;
            int endTime=(endHours*100)+endMins;
            int currTime=(currHours*100)+currMins;
            Log.e("times","startTime :"+startTime+" , currTime :"+currTime+" , endTIme: "+endTime);

            if(currTime>=startTime&&currTime<=endTime)
            {
                return false;
            }



        }
        return  true;
    }

    public void updateTextView()
    {
        if(isFree())
        {
            txtStatus.setText("Free!");
            txtStatus.setTextColor(ContextCompat.getColor(StatusActivity.this, R.color.colorVaccant));

        }
        else
        {
            txtStatus.setText("Occupied!");
            txtStatus.setTextColor(ContextCompat.getColor(StatusActivity.this, R.color.colorOccupied));
        }
    }
}
