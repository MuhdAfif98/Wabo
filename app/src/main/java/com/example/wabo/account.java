package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class account extends AppCompatActivity {

    private TextView tvemail,tvic,tvphone,tvaddress,tvname,tvusername,Heirin, Heiric;
    ImageView no;
    AppCompatButton viewBtn;
    String UID;
    ListView heirs;
    String heirfrom;


    List<Heir> heirList;
    Heir heir;


    DatabaseReference reff, reff2, reff3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        String username = getIntent().getStringExtra("username");

        //bawak id username masuk dalam class
        tvemail = findViewById(R.id.emailin);
        tvname = findViewById(R.id.namein);
        tvic = findViewById(R.id.ICin);
        tvphone = findViewById(R.id.phonein);
        tvaddress = findViewById(R.id.addressin);
        Heirin = findViewById(R.id.Heirin);
        Heiric = findViewById(R.id.Heiric);
        no = findViewById(R.id.no);

        viewBtn = findViewById(R.id.viewBtn);

        reff = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Users");
        reff2 = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Heir");


        DAuser dAuser = new DAuser();


        heirfrom = username;

        Query queryuname = reff.orderByChild("username").equalTo(username);
        Query queryuname2 = reff2.orderByChild("heirfrom").equalTo(heirfrom);

        queryuname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    //call variable from database

                    UID = dataSnapshot.getKey();
                    String emailfirebase = dataSnapshot.child("emailAddress").getValue().toString();
                    String namefirebase = dataSnapshot.child("name").getValue().toString();
                    String icfirebase = dataSnapshot.child("nric").getValue().toString();
                    String phonefirebase = dataSnapshot.child("phone").getValue().toString();
                    String addressfirebase = dataSnapshot.child("address").getValue().toString();


                    tvemail.setText(emailfirebase);
                    tvname.setText(namefirebase);
                    tvphone.setText(phonefirebase);
                    tvaddress.setText(addressfirebase);
                    tvic.setText(icfirebase);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        queryuname2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    //call variable from database

                    UID = dataSnapshot.getKey();
                    String heirnamefirebase = dataSnapshot.child("heirsname").getValue().toString();
                    String heiricfirebase = dataSnapshot.child("heirsic").getValue().toString();
                    String heiraddfirebase = dataSnapshot.child("heirsadd").getValue().toString();


                    Heirin.setText(heirnamefirebase);
                    Heiric.setText(heiricfirebase);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        heirs = findViewById(R.id.heirs);

        heirList = new ArrayList<>();

        reff3 = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Heir");

        Query queryheir = reff3.orderByChild("heirfrom").equalTo(heirfrom);

        queryheir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                heirList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    heir = dataSnapshot.getValue(Heir.class);
                    heirList.add(heir);

                }

                list_view_heir_adapter adapter = new list_view_heir_adapter(account.this,heirList);
                heirs.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account.this,account_update.class);
                intent.putExtra("username",username);

                startActivity(intent);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(account.this,MainActivity.class);
                intent.putExtra("username",username);

                startActivity(intent);

            }
        });




    }
}