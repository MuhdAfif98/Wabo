package com.example.wabo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_heir extends AppCompatActivity {

String heirfrom;
DatabaseReference reff;
    ImageView no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_heir);

        String username = getIntent().getStringExtra("username");
        reff = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Users");

        final EditText heirsname = findViewById(R.id.heirnamein);
        final EditText heirsic = findViewById(R.id.heiricin);
        final EditText heirsadd= findViewById(R.id.heiraddin);
        no = findViewById(R.id.no);
        AppCompatButton addheirBtn = findViewById(R.id.addheirBtn);

        DAheir dAheir = new DAheir();

        heirfrom = username;

        addheirBtn.setOnClickListener(v ->
        {
            //insert data to database

            Heir he = new Heir(heirsname.getText().toString(),heirsic.getText().toString(),heirsadd.getText().toString(),heirfrom);
            dAheir.add(he).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "record as insert", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(add_heir.this, account_update.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }).addOnFailureListener(er ->
            {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(add_heir.this,account.class);
                intent.putExtra("username",username);

                startActivity(intent);

            }
        });

    }
}