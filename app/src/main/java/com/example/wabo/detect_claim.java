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

    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_claim);


        String username = getIntent().getStringExtra("username");

        home = findViewById(R.id.home);
        claim = findViewById(R.id.claim);
        willIC = findViewById(R.id.ic);
        displaystatus = findViewById(R.id.displaystatus);

        reff = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("willDB");

        claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ic = willIC.getText().toString();

                Query query = reff.orderByChild("willIC").equalTo(ic);


                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for (DataSnapshot dataSnapshot:snapshot.getChildren()) {

                                UID = dataSnapshot.getKey();
                                displaystatus.setText(dataSnapshot.child("willStatus").getValue().toString());

                                if(displaystatus.getText().toString().equals("Verified")){

                                    Intent intent = new Intent(detect_claim.this,will_claim.class);
                                    intent.putExtra("username",username);
                                    intent.putExtra("willIC",ic);


                                    startActivity(intent);

                                }else
                                {

                                    //Intent intent = new Intent(detect_claim.this,detect_claim.class);
                                    //intent.putExtra("username",username);


                                   // startActivity(intent);

                                    displaystatus.setText(dataSnapshot.child("willStatus").getValue().toString());

                                }

                            }



                            Log.e(TAG, "masuk: ");
                            Log.e(TAG, ic);

                            HashMap hashMap = new HashMap();

                            hashMap.put("willIC",ic);
                            hashMap.put("willStatus","Claim");

                          /*  reff.child(UID).updateChildren(hashMap).addOnSuccessListener(suc ->
                            {
                                Toast.makeText(getApplicationContext(), "record as update", Toast.LENGTH_SHORT).show();

                               Intent intent = new Intent(detect_claim.this,success_claim.class);
                                intent.putExtra("username",username);

                                startActivity(intent);

                            }).addOnFailureListener(er ->
                            {
                                Toast.makeText(getApplicationContext(), ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                            });*/
                        }
                        else{

                            Log.e(TAG, "takmasuk: ");
                            Log.e(TAG, ic);
                            displaystatus.setText("Sorry no will for you");


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detect_claim.this,MainActivity.class);
                intent.putExtra("username",username);


                startActivity(intent);
            }
        });




    }

}