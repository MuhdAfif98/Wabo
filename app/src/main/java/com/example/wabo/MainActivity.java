package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
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

public class MainActivity extends AppCompatActivity {
    TextView usernameText;
    Button logout;
    String userID;
    CardView writewill,readwill;

    FirebaseFirestore firestore;
    FirebaseDatabase firebase;
    DatabaseReference df;
    FirebaseAuth auth;
    private static final String TAG = "MyActivity";
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logoutBtn);
        usernameText = findViewById(R.id.usernameText);
        writewill = findViewById(R.id.writewill);

        readwill = findViewById(R.id.readwill);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        firebase = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app");
        df = firebase.getReference("Users");

        getData();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = auth.getCurrentUser().getUid();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),StartPage.class));
                finish();
                }
    });
    }

    private void getData() {

        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userID = auth.getCurrentUser().getUid();
                String username = snapshot.child(userID).child("username").getValue(String.class);
                usernameText.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        readwill.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, viewWillAttorney.class));
            }
        });
    }


}

