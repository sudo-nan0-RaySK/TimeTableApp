package com.example.legendarychild.timetableapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class MainActivity extends AppCompatActivity {

    Button addButton;
    Button frndsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton= (Button)findViewById(R.id.addButton);
        frndsButton=(Button)findViewById(R.id.frndsButton);




        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,AddTTActivity.class);
                startActivity(intent);
            }
        });

        frndsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,FriendsListActivity.class);
                startActivity(intent);

            }
        });
    }
}
