package com.example.wabo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class profileRegistration extends AppCompatActivity {

    EditText name,nric,phone,address;
    Button register;

    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;

    //realtime db
    FirebaseDatabase firebase;
    FirebaseAuth auth;
    DatabaseReference df;
    User user;

    //for the deprecated thing
    ActivityResultLauncher<String> mGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_registration);

        firebase = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/");
        df = firebase.getReference("Users");
        user = new User();

        name = findViewById(R.id.name);
        nric = findViewById(R.id.nric);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);

        register = findViewById(R.id.registerBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Nric = nric.getText().toString();
                String Phone = phone.getText().toString();
                String Address = address.getText().toString();

                FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();

                df.child(auth.getUid()).child("name").setValue(Name);
                df.child(auth.getUid()).child("nric").setValue(Nric);
                df.child(auth.getUid()).child("phone").setValue(Phone);
                df.child(auth.getUid()).child("address").setValue(Address);

                Toast.makeText(profileRegistration.this, "Data added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });
    }
}

