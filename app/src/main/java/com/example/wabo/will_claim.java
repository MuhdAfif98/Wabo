package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class will_claim extends AppCompatActivity {

    EditText jumlah, description, status, title, ic, penerima;
    AppCompatButton add;
    DatabaseReference reff;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_will_claim);

        String username = getIntent().getStringExtra("username");
        String IC = getIntent().getStringExtra("willIC");

        jumlah = findViewById(R.id.jumlah);
        description = findViewById(R.id.description);
        status = findViewById(R.id.status);
        title = findViewById(R.id.title);
        ic = findViewById(R.id.ic);
        penerima = findViewById(R.id.penerima);
        add = findViewById(R.id.addbtn);

        reff = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("willDB");


        Query queryuname = reff.orderByChild("willIC").equalTo(IC);

        queryuname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    UID = dataSnapshot.getKey();

                    String discriptionfirebase = dataSnapshot.child("willDescription").getValue().toString();
                    String icfirebase = dataSnapshot.child("willIC").getValue().toString();
                    String jumlahfirebase = dataSnapshot.child("willJumlah").getValue().toString();
                    String penerimafirebase = dataSnapshot.child("willPenerima").getValue().toString();
                    String statusfirebase = dataSnapshot.child("willStatus").getValue().toString();
                    String titlefirebase = dataSnapshot.child("willTitle").getValue().toString();


                    description.setText(discriptionfirebase);
                    ic.setText(icfirebase);
                    jumlah.setText(jumlahfirebase);
                    penerima.setText(penerimafirebase);
                    status.setText(statusfirebase);
                    title.setText(titlefirebase);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap hashMap = new HashMap();

                hashMap.put("willStatus","Claim");

                reff.child(UID).updateChildren(hashMap).addOnSuccessListener(suc ->
                {
                    Toast.makeText(getApplicationContext(), "record as update", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(will_claim.this,success_claim.class);
                    intent.putExtra("username",username);

                    startActivity(intent);

                }).addOnFailureListener(er ->
                {
                    Toast.makeText(getApplicationContext(), ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });

            }
        });

      //  add.setOnClickListener(v ->
       // {

      //  });


    }
}