package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class viewWillAttorneyVerified extends AppCompatActivity{

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    Query WaboDB;
    ListView idRVVerifiedWill;
    List<Will> willList;
    Will will;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_attorney_verified);

        //show adaptor
        idRVVerifiedWill = findViewById(R.id.idRVVerifiedWill);
        willList = new ArrayList<>();

        WaboDB = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("WillDB").orderByChild("willStatus").equalTo("Verified");
        Query query = WaboDB;//firebase query
        query.addValueEventListener(new ValueEventListener() {  //valueeventlistener..com.google
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                willList.clear();

                for(DataSnapshot studentDatasnap : datasnapshot.getChildren()){
                    Will will = studentDatasnap.getValue(Will.class);
                    willList.add(will);

                }

                willVerifiedAdapter adapter1 = new willVerifiedAdapter(viewWillAttorneyVerified.this,willList);
                idRVVerifiedWill.setAdapter(adapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //abih show adaptor

        //Click on listview onclicklistener
        idRVVerifiedWill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Will will = willList.get(i);
                String willTitle = will.getWillTitle();
                Intent intent = new Intent (getApplicationContext(),viewWillAttorneyVerifiedMore.class);
                intent.putExtra("willTitle",willTitle);
                startActivity(intent);
            }
        });
        //abih Click on listview onclicklistener

        Button buttonReturn = (Button) findViewById(R.id.buttonReturn);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), viewWillAttorney.class));
            }
        });

    }
}