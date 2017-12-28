package com.example.legendarychild.timetableapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FriendsListActivity extends AppCompatActivity {
    ListView frndsListView;
    DatabaseReference databaseFriends;
    DatabaseReference databaseTimeTable;
    List<Friend> frndList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        frndsListView=(ListView)findViewById(R.id.frndsListView);
        databaseFriends= FirebaseDatabase.getInstance().getReference("friends");

        frndList= new ArrayList<>();
        frndsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(FriendsListActivity.this,StatusActivity.class);
                Bundle bundle = new Bundle();
                Friend friend= frndList.get(i);
                bundle.putString("currNode",friend.getId());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseFriends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                frndList.clear();
                for(DataSnapshot frndsSnapshot:dataSnapshot.getChildren())
                {
                     Friend frnd = frndsSnapshot.getValue(Friend.class);
                     frndList.add(frnd);
                }
                ListOfFriends adapter= new ListOfFriends(FriendsListActivity.this,frndList);
                frndsListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
