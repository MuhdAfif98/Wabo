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

public class viewWillAttorneyUnverified extends AppCompatActivity{

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    Query WaboDB;
    ListView idRVUnverifiedWill;
    List<will> willList;
    will will;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_will_attorney_unverified);

                //show adaptor
        idRVUnverifiedWill = findViewById(R.id.idRVUnverifiedWill);
        willList = new ArrayList<>();

        WaboDB = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("WillDB").orderByChild("willStatus").equalTo("Unverified");
        Query query = WaboDB;//firebase query
        query.addValueEventListener(new ValueEventListener() {  //valueeventlistener..com.google
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                willList.clear();

                for(DataSnapshot studentDatasnap : datasnapshot.getChildren()){
                    will will = studentDatasnap.getValue(will.class);
                    willList.add(will);

                }

                willUnverifiedAdapter adapter1 = new willUnverifiedAdapter(viewWillAttorneyUnverified.this,willList);
                idRVUnverifiedWill.setAdapter(adapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //abih show adaptor

        //Click on listview onclicklistener
        idRVUnverifiedWill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                will will = willList.get(i);
                String willTitle = will.getWillTitle();
                Intent intent = new Intent (getApplicationContext(),viewWillAttorneyUnverifiedMore.class);
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),viewWillAttorney.class));
    }
}