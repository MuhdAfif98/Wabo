package com.example.wabo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAuser {

    private DatabaseReference databaseReference;

    public DAuser(){

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://wabo-36023-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = db.getReference(Users.class.getSimpleName());
    }

    //add user
    public Task<Void> add(Users us)
    {

        return databaseReference.push().setValue(us);

    }

    //update user
    public Task<Void> update(String key, HashMap hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    //delete user
    public Task<Void> remove(String key)
    {
        return  databaseReference.child(key).removeValue();
    }

}
