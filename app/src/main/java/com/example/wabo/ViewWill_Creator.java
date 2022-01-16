package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ViewWill_Creator extends AppCompatActivity {

    TextView usernameText;
    String userID;
    ImageView verifiedwillC, notverifiedwillC, claimedwillC;

    Button mainpageBtn;
    FirebaseFirestore firestore;
    DatabaseReference df;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_creator);

        mainpageBtn = findViewById(R.id.mainpageBtn);
        usernameText = findViewById(R.id.usernameText);
        verifiedwillC = findViewById(R.id.verifiedwillC);
        notverifiedwillC = findViewById(R.id.notverifiedwillC);
        claimedwillC = findViewById(R.id.claimedwillC);

        auth = FirebaseAuth.getInstance();

        getData();

        //Button homepage
        mainpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        //Image Verified
        verifiedwillC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewWill_Creator.this,ViewWill_Creator_Verify.class);
                startActivity(intent);
            }
        });
        //Image NotVerified
        notverifiedwillC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewWill_Creator.this,ViewWill_Creator_NotVerify.class);
                startActivity(intent);
            }
        });
        //Image Claimed
        claimedwillC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewWill_Creator.this,ViewWill_Creator_Claimed.class);
                startActivity(intent);
            }
        });
        //Image Claimed
        //Image Claimed
        //Image Claimed
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

    }
}