package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;

public class viewWillAttorneyUnverifiedMore extends AppCompatActivity{

    String willTitle, willID;
    TextView title;
    EditText penerima, jumlah, wasiat, media, status;
    Query mbase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_attorney_unverified_more);

        willTitle = getIntent().getStringExtra("willTitle");

        title = findViewById(R.id.textTitle);
        penerima = findViewById(R.id.textPenerima);
        jumlah = findViewById(R.id.textJumlah);
        wasiat = findViewById(R.id.textDescription);
        media = findViewById(R.id.textMedia);
        status = findViewById(R.id.textStatus);

        mbase = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("WillDB").orderByChild("willTitle").equalTo(willTitle);

        DatabaseReference reff = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("WillDB");
        Query query = mbase;

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    //call from database
                    willID = dataSnapshot.getKey();//unique

                    String willTitleFirebase = dataSnapshot.child("willTitle").getValue().toString();
                    String willPenerimaFirebase = dataSnapshot.child("willPenerima").getValue().toString();
                    String willJumlahFirebase = dataSnapshot.child("willJumlah").getValue().toString();
                    String willWasiatFirebase = dataSnapshot.child("willDescription").getValue().toString();
                    String willMediaFirebase = dataSnapshot.child("willIC").getValue().toString();
                    String willStatusFirebase = dataSnapshot.child("willStatus").getValue().toString();

                    title.setText(willTitleFirebase);
                    penerima.setText(willPenerimaFirebase);
                    jumlah.setText(willJumlahFirebase);
                    wasiat.setText(willWasiatFirebase);
                    media.setText(willMediaFirebase);
                    status.setText(willStatusFirebase);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button reject = (Button) findViewById(R.id.buttonReject);
        Button verify = (Button) findViewById(R.id.buttonVerify);


        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap hashMap = new HashMap();
                hashMap.put("willStatus", "Rejected");

                reff.child(willID).updateChildren(hashMap);

                startActivity(new Intent(viewWillAttorneyUnverifiedMore.this, viewWillAttorneyUnverified.class));
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap hashMap = new HashMap();
                hashMap.put("willStatus", "Verified");

                reff.child(willID).updateChildren(hashMap);

                startActivity(new Intent(viewWillAttorneyUnverifiedMore.this, viewWillAttorneyUnverified.class));
            }
        });



    }

}