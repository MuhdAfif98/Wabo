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

public class ViewWill_Creator_NotVerify2 extends AppCompatActivity {
    //fetch listview
    String TitleNotVerify;
    Query WaboDB;
    will Will1;
    TextView CU2willtitle, CU2willdescription, CU2willpenerima, CU2willjumlah, CU2willwasiat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_creator_not_verify2);

        //fetch listview
        TitleNotVerify = getIntent().getStringExtra("TitleNotVerify");
        //findby
        CU2willtitle = findViewById(R.id.CU2willtitle);
        CU2willdescription = findViewById(R.id.CU2willdescription);
        CU2willjumlah = findViewById(R.id.CU2willjumlah);
        CU2willpenerima = findViewById(R.id.CU2willpenerima);

        WaboDB = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("WillDB").orderByChild("willTitle").equalTo(TitleNotVerify);
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

                    CU2willtitle.setText(willTitle);
                    CU2willdescription.setText(willDescription);
                    CU2willjumlah.setText(willJumlah);
                    CU2willpenerima.setText(willPenerima);
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