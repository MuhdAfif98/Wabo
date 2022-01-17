package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.HashMap;

public class detect_claim extends AppCompatActivity {


    private AppCompatButton home, claim;
    private EditText willIC;
    private TextView displaystatus;
    private static final String TAG = "MyActivity";
    String UID;

    Query reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_claim);


        home = findViewById(R.id.home);
        claim = findViewById(R.id.claim);
        willIC = findViewById(R.id.ic);
        displaystatus = findViewById(R.id.displaystatus);

        reff = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("WillDB").orderByChild("willIC");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IC = willIC.getText().toString();
                Query query = reff.equalTo(IC);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                            UID = dataSnapshot.getKey();

                            displaystatus.setText(dataSnapshot.child("willStatus").getValue().toString());

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });




    }

}