package com.example.legendarychild.timetableapp;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;

public class TTFormActivity extends AppCompatActivity {
    Button addPeriod;
    Button addBtn;
    TimePicker timePicker;
    EditText addSubjectName;
    String currNode;
    DatabaseReference subjRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttform);

        addPeriod = (Button) findViewById(R.id.addPeriod);
        addBtn = (Button) findViewById(R.id.addBtn);
        addSubjectName = (EditText) findViewById(R.id.addSubjectName);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);


        Bundle bundle = getIntent().getExtras();
        currNode = bundle.getString("currNode");


        subjRef = FirebaseDatabase.getInstance().getReference("timetable").child(currNode);

        Toast.makeText(TTFormActivity.this, currNode, Toast.LENGTH_LONG).show();

        addPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addToTT();

                addSubjectName.setText("");


            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TTFormActivity.this.finish();
            }
        });

    }

    public  void addToTT()
    {



        String name= addSubjectName.getText().toString();
        String startTimeHour= String.valueOf(timePicker.getHour());
        String startTimeMin=String.valueOf(timePicker.getMinute());

        if(!TextUtils.isEmpty(name)) {
            String id = subjRef.push().getKey();
            Period period= new Period(name,startTimeHour,startTimeMin);
            subjRef.child(id).setValue(period);
            Toast.makeText(TTFormActivity.this, "Subject Added!", Toast.LENGTH_LONG).show();


        }
        else
        {
            Toast.makeText(TTFormActivity.this, "Write Something!", Toast.LENGTH_LONG).show();

        }


    }
}
