package com.example.legendarychild.timetableapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddTTActivity extends AppCompatActivity {
    Button proceedBtn;
    EditText regNo;
    EditText name;
    DatabaseReference friendsReference;
    String id="ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tt);

        proceedBtn=(Button)findViewById(R.id.proceedBtn);
        regNo=(EditText)findViewById(R.id.regNo);
        name=(EditText)findViewById(R.id.name);
        friendsReference= FirebaseDatabase.getInstance().getReference("friends");

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFriend();
                Intent intent = new Intent(AddTTActivity.this,TTFormActivity.class);
                //Toast.makeText(AddTTActivity.this,"Random Key Value : "+id,Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("currNode",id);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });
    }

    protected void addFriend()
    {
        String regisTrationNumber=String.valueOf(regNo.getText());
        String Name= String.valueOf(name.getText());

        if((!TextUtils.isEmpty(regisTrationNumber))&&(!TextUtils.isEmpty(Name)))
        {
            id= friendsReference.push().getKey();
            Friend fr= new Friend(Name,regisTrationNumber,id);
           friendsReference.child(id).setValue(fr);

            Toast.makeText(AddTTActivity.this,"Friend added",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(AddTTActivity.this,"Enter Something Please",Toast.LENGTH_LONG).show();
        }
    }
}
