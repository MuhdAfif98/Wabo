package com.example.wabo;

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
import com.google.firebase.database.DatabaseReference;
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

        //show idd
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userID = auth.getCurrentUser().getUid();
        DocumentReference df = firestore.collection("Users").document(userID);
        df.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                usernameText.setText(value.getString("Username"));
            }
        });
        //abih show id

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
}