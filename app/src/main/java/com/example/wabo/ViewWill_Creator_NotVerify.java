package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class ViewWill_Creator_NotVerify extends AppCompatActivity {

    TextView usernameText;
    String userID;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Query WaboDB;
    ListView myListView;
    List<will> ViewWill_Creator_ListNotVerify;
    will Will1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_creator_not_verify);

        //show adaptor
        myListView = findViewById(R.id.myListView);
        ViewWill_Creator_ListNotVerify = new ArrayList<>();

        WaboDB = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("WillDB").orderByChild("willStatus").equalTo("Unverified");
        Query query = WaboDB;//firebase query
        query.addValueEventListener(new ValueEventListener() {  //valueeventlistener..com.google
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ViewWill_Creator_ListNotVerify.clear();

                for(DataSnapshot studentDatasnap : datasnapshot.getChildren()){
                    will Will1 = studentDatasnap.getValue(will.class);
                    ViewWill_Creator_ListNotVerify.add(Will1);

                }

                NotVerifyAdaptor adapter = new NotVerifyAdaptor(ViewWill_Creator_NotVerify.this,ViewWill_Creator_ListNotVerify);
                myListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //abih show adaptor

        //Click on listview onclicklistener
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                will Will1 = ViewWill_Creator_ListNotVerify.get(i);
                String willTitle = Will1.getWillTitle();
                Intent intent = new Intent (getApplicationContext(),ViewWill_Creator_NotVerify2.class);
                intent.putExtra("TitleNotVerify",willTitle);
                startActivity(intent);
            }
        });
        //abih Click on listview onclicklistener


    }
}