package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

public class add_will extends AppCompatActivity {

    EditText jumlah, description, status, title, ic, penerima;
    AppCompatButton add;
    DatabaseReference reff;
    long maxid;
    String userID;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_will);

        jumlah = findViewById(R.id.jumlah);
        description = findViewById(R.id.description);

        title = findViewById(R.id.title);
        ic = findViewById(R.id.ic);
        penerima = findViewById(R.id.penerima);
        add = findViewById(R.id.addbtn);
        auth = FirebaseAuth.getInstance();
//        firebase = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app");
//        df = firebase.getReference("Users");


        reff = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("willDB");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Please verify")
                        .setDescription("User authentication is required")
                        .setNegativeButtonText("Cancel")
                        .build();
                getPrompt().authenticate(promptInfo);

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
                userID = auth.getCurrentUser().getUid();

                String jumlah1 = jumlah.getText().toString();
                String description1 = description.getText().toString();
                String status1 = "Unverified";
                String title1 = title.getText().toString();
                String ic1 = ic.getText().toString();
                String penerima1 = penerima.getText().toString();
                String willOwner = userID;


                willDB.setWillJumlah(jumlah1);
                willDB.setWillDescription(description1);
                willDB.setWillStatus(status1);
                willDB.setWillTitle(title1);
                willDB.setWillIC(ic1);
                willDB.setWillPenerima(penerima1);
                willDB.setWillOwner(willOwner);

                reff.push().setValue(willDB);


            }
        });




    }
    private void notifyUser(String abc){
        Toast.makeText(this, abc, Toast.LENGTH_SHORT).show();
    }

    private BiometricPrompt getPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser(errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                notifyUser("Add Will succeed");
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyUser("Authentication failed");
            }
        };

        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);
        return biometricPrompt;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
