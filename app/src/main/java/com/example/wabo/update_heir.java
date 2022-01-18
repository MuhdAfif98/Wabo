package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class update_heir extends AppCompatActivity {

    private EditText heirnamein, heiricin, heiraddin;
    String UID;
    ImageView no;

    DatabaseReference reff2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_heir);


        String heiric = getIntent().getStringExtra("heiric");
        String username = getIntent().getStringExtra("username");
        String heirfrom = getIntent().getStringExtra("heirfrom");

        heirnamein = findViewById(R.id.heirnamein);
        heiraddin = findViewById(R.id.heiraddin);
        heiricin = findViewById(R.id.heiricin);
        no = findViewById(R.id.no);

        AppCompatButton updateheirBtn = findViewById(R.id.updateheirBtn);

        reff2 = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Heir");

        DAheir dAheir = new DAheir();

        Query queryuname2 = reff2.orderByChild("heirsic").equalTo(heiric);


        queryuname2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    //call variable from database

                    UID = dataSnapshot.getKey();
                    String heirnamefirebase = dataSnapshot.child("heirsname").getValue().toString();
                    String heiricfirebase = dataSnapshot.child("heirsic").getValue().toString();
                    String heiraddfirebase = dataSnapshot.child("heirsadd").getValue().toString();

                    heirnamein.setText(heirnamefirebase);
                    heiricin.setText(heiricfirebase);
                    heiraddin.setText(heiraddfirebase);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updateheirBtn.setOnClickListener(v ->
        {
            HashMap<String,Object> hashMap = new HashMap<>();

            hashMap.put("heirsname", heirnamein.getText().toString());
            hashMap.put("heirsic", heiricin.getText().toString());
            hashMap.put("heirsadd", heiraddin.getText().toString());


            dAheir.update(UID,hashMap).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "record as update", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->
            {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });

            Intent intent = new Intent(update_heir.this,account_update.class);
            intent.putExtra("username",username);
            startActivity(intent);

        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(update_heir.this,account.class);
                intent.putExtra("username",username);

                startActivity(intent);

            }
        });



    }

}