package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class ViewWill_Creator_Verify extends AppCompatActivity {

    TextView usernameText;
    String userID;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    Query WaboDB;
    ListView myListView2;
    List<will> ViewWill_Creator_ListVerify;
    will Will1;
    Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_creator_verify);


        //show adaptor
        myListView2 = findViewById(R.id.myListView2);
        backbtn = findViewById(R.id.backbtn);
        ViewWill_Creator_ListVerify = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();

        WaboDB = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("willDB").orderByChild("willOwner").equalTo(userID);
        Query query = WaboDB;//firebase query
        query.addValueEventListener(new ValueEventListener() {  //valueeventlistener..com.google
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ViewWill_Creator_ListVerify.clear();

                for(DataSnapshot studentDatasnap : datasnapshot.getChildren()){
                    if (studentDatasnap.child("willStatus").getValue().toString().matches("Verified")) {
                        will Will1 = studentDatasnap.getValue(will.class);
                        ViewWill_Creator_ListVerify.add(Will1);
                    }

                }

                VerifyAdaptor adapter2 = new VerifyAdaptor(ViewWill_Creator_Verify.this,ViewWill_Creator_ListVerify);
                myListView2.setAdapter(adapter2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewWill_Creator.class));
                finish();
            }
        });

        //abih show adaptor

        //Click on listview onclicklistener
        myListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                will Will1 = ViewWill_Creator_ListVerify.get(i);
                String willTitle = Will1.getWillTitle();
                Intent intent = new Intent (getApplicationContext(),ViewWill_Creator_Verify2.class);
                intent.putExtra("TitleVerify",willTitle);
                startActivity(intent);
            }
        });
        //abih Click on listview onclicklistener
    }
}