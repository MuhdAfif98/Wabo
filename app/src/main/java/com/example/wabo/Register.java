package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText username, email, password;
    AppCompatButton registerBtn, goToLogin;
    boolean valid = true;
    FirebaseAuth auth;
    FirebaseDatabase firebase;
    DatabaseReference df;
    RadioButton isUserBox, isAttorneyBox;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        firebase = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/");
        df = firebase.getReference("Users");
        user = new User();

        username = findViewById(R.id.registerUsername);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);

        registerBtn = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.gotoLogin);

        isUserBox = findViewById(R.id.isUser);
        isAttorneyBox = findViewById(R.id.isAttorney);

        //CHECK BOXES LOGIC
        isUserBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    isAttorneyBox.setChecked(false);
                }
            }
        });

        isAttorneyBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    isUserBox.setChecked(false);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //CHECKBOX VALIDATION
                if (!(isAttorneyBox.isChecked() || isUserBox.isChecked())) {
                    Toast.makeText(Register.this, "Select the account type", Toast.LENGTH_LONG).show();
                    return;
                }


                if (valid) {
                    //START USER REGISTRATION
                    auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser auths = FirebaseAuth.getInstance().getCurrentUser();
                            Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                            String Username = username.getText().toString();
                            String Email = email.getText().toString();
                            String Password = password.getText().toString();

                            user.setUsername(Username);
                            user.setEmailAddress(Email);
                            user.setPassword(Password);

                            //SPECIFY ROLES
                            if(isUserBox.isChecked()){
                                user.setRole("isUser");
                            }
                            else if(isAttorneyBox.isChecked()){
                                user.setRole("isAttorney");
                            }
                            else{
                                return;
                            }
                            df.child(auths.getUid()).setValue(user);

                            if(isUserBox.isChecked() || isAttorneyBox.isChecked()){
                                startActivity(new Intent(getApplicationContext(), profileRegistration.class));
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, "Failed to Create Account", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }

    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }
}