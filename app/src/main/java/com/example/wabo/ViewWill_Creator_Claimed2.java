package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewWill_Creator_Claimed2 extends AppCompatActivity {
    //fetch listview
    String TitleClaimed;
    Query WaboDB;
    Will Will1;
    TextView CC2willtitle, CC2willdescription, CC2willpenerima, CC2willjumlah, CC2willwasiat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_creator_claimed2);

        //fetch listview
        TitleClaimed = getIntent().getStringExtra("TitleClaimed");
        //findby
        CC2willtitle = findViewById(R.id.CC2willtitle);
        CC2willdescription = findViewById(R.id.CC2willdescription);
        CC2willjumlah = findViewById(R.id.CC2willjumlah);
        CC2willpenerima = findViewById(R.id.CC2willpenerima);
        //
        WaboDB = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("WillDB").orderByChild("willTitle").equalTo(TitleClaimed);
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

                    CC2willtitle.setText(willTitle);
                    CC2willdescription.setText(willDescription);
                    CC2willjumlah.setText(willJumlah);
                    CC2willpenerima.setText(willPenerima);
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