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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText username, email, password;
    AppCompatButton registerBtn, goToLogin;
    boolean valid = true;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    RadioButton isUserBox, isAttorneyBox, isHeirBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        username = findViewById(R.id.registerUsername);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);

        registerBtn = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.gotoLogin);

        isUserBox = findViewById(R.id.isUser);
        isAttorneyBox = findViewById(R.id.isAttorney);
        isHeirBox = findViewById(R.id.isHeir);

        //CHECK BOXES LOGIC
        isUserBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    isAttorneyBox.setChecked(false);
                    isHeirBox.setChecked(false);
                }
            }
        });

        isAttorneyBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    isUserBox.setChecked(false);
                    isHeirBox.setChecked(false);
                }
            }
        });

        isHeirBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    isUserBox.setChecked(false);
                    isAttorneyBox.setChecked(false);
                }
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(username);
                checkField(email);
                checkField(password);

                //CHECKBOX VALIDATION
                if (!(isAttorneyBox.isChecked() || isUserBox.isChecked() || isHeirBox.isChecked())) {
                    Toast.makeText(Register.this, "Select the account type", Toast.LENGTH_LONG).show();
                    return;
                }


                if (valid) {
                    //START USER REGISTRATION
                    auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                            DocumentReference df = firestore.collection("Users").document(user.getUid());
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("Username", username.getText().toString());
                            userInfo.put("Email", email.getText().toString());

                            //SPECIFY ROLES
                            if(isUserBox.isChecked()){
                                userInfo.put("isUser", "1");
                            }
                            else if(isAttorneyBox.isChecked()){
                                userInfo.put("isAttorney", "1");
                            }
                            else if(isHeirBox.isChecked()){
                                userInfo.put("isHeir", "1");
                            }
                            else{
                                return;
                            }

                            df.set(userInfo);

                            if(isUserBox.isChecked()){
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                            else if(isAttorneyBox.isChecked()){
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                            else if(isHeirBox.isChecked()){
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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