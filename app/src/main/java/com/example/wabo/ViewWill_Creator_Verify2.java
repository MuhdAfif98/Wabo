package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewWill_Creator_Verify2 extends AppCompatActivity {
    String TitleVerify;
    Query WaboDB;
    Will Will1;
    TextView CV2willtitle, CV2willdescription, CV2willpenerima, CV2willjumlah, CV2willwasiat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_creator_verify2);

        //fetch listview
        TitleVerify = getIntent().getStringExtra("TitleVerify");
        WaboDB = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("WillDB").orderByChild("willTitle").equalTo(TitleVerify);
        //findby
        CV2willtitle = findViewById(R.id.CV2willtitle);
        CV2willdescription = findViewById(R.id.CV2willdescription);
        CV2willjumlah = findViewById(R.id.CV2willjumlah);
        CV2willpenerima = findViewById(R.id.CV2willpenerima);
        Query query = WaboDB;//firebase query
        query.addValueEventListener(new ValueEventListener() {  //valueeventlistener..com.google
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot studentDatasnap : datasnapshot.getChildren()){
                    //declaration
                    String willTitle = studentDatasnap.child("willTitle").getValue().toString();
                    String willDescription = studentDatasnap.child("willDescription").getValue().toString();
                    String willJumlah = studentDatasnap.child("willJumlah").getValue().toString();
                    String willPenerima = studentDatasnap.child("willPenerima").getValue().toString();

                    CV2willtitle.setText(willTitle);
                    CV2willdescription.setText(willDescription);
                    CV2willjumlah.setText(willJumlah);
                    CV2willpenerima.setText(willPenerima);
                    //
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        //abih listview
    }
}