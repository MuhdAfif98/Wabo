package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
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
    DatabaseReference WaboDB;
    ListView myListView;
    List<Will> ViewWill_Creator_ListNotVerify;
    Will Will1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_creator_not_verify);

        //show idd
        usernameText = findViewById(R.id.usernameText);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userID = auth.getCurrentUser().getUid();
        DocumentReference df = firestore.collection("Users").document(userID);
        df.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                usernameText.setText(value.getString("Username"));
            }
        });
        //abih show id

        //show adaptor
        myListView = findViewById(R.id.myListView);
        ViewWill_Creator_ListNotVerify = new ArrayList<>();

        WaboDB = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("WillDB");
        Query query = WaboDB;//firebase query
        query.addValueEventListener(new ValueEventListener() {  //valueeventlistener..com.google
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ViewWill_Creator_ListNotVerify.clear();

                for(DataSnapshot studentDatasnap : datasnapshot.getChildren()){
                    Will Will1 = studentDatasnap.getValue(Will.class);
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


    }
}