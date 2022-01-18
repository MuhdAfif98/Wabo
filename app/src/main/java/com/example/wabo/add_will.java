package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class add_will extends AppCompatActivity {

    EditText jumlah, description, status, title, ic, penerima;
    AppCompatButton add;
    DatabaseReference reff;
    long maxid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_will);

        jumlah = findViewById(R.id.jumlah);
        description = findViewById(R.id.description);
        status = findViewById(R.id.status);
        title = findViewById(R.id.title);
        ic = findViewById(R.id.ic);
        penerima = findViewById(R.id.penerima);
        add = findViewById(R.id.addbtn);


        reff = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("willDB");



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            maxid = (snapshot.getChildrenCount());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                WillDB willDB = new WillDB();

                String jumlah1 = jumlah.getText().toString();
                String description1 = description.getText().toString();
                String status1 = status.getText().toString();
                String title1 = title.getText().toString();
                String ic1 = ic.getText().toString();
                String penerima1 = penerima.getText().toString();


                willDB.setWillJumlah(jumlah1);
                willDB.setWillDescription(description1);
                willDB.setWillStatus(status1);
                willDB.setWillTitle(title1);
                willDB.setWillIC(ic1);
                willDB.setWillPenerima(penerima1);

                reff.child("Will"+String.valueOf(maxid+1)).setValue(willDB);

            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
