package com.example.wabo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class viewWillAttorney extends AppCompatActivity {

    Button willUnverified, willVerified, willRejected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_attorney);

        willUnverified = findViewById(R.id.buttonWillUnverified);
        willVerified = findViewById(R.id.buttonWillVerified);
        willRejected = findViewById(R.id.buttonWillRejected);

        willUnverified.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(viewWillAttorney.this, viewWillAttorneyUnverified.class));
                finish();
            }
        });

        willVerified.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //startActivity(new Intent(viewWillAttorney.this, viewWillAttorneyVerified.class));
                Intent intent = new Intent(viewWillAttorney.this,viewWillAttorneyVerified.class);
                startActivity(intent);
                finish();
            }
        });

        willRejected.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(viewWillAttorney.this, viewWillAttorneyRejected.class));
            }
        });

        Button buttonReturn = (Button) findViewById(R.id.buttonReturn);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
