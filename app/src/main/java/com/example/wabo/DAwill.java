package com.example.wabo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAwill {

    private DatabaseReference databaseReference;

    public DAwill(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(WillDB.class.getSimpleName());
    }

    public Task<Void> add(WillDB will)
    {

        return databaseReference.push().setValue(will);

    }

    //update user
    public Task<Void> update(String key, HashMap<String, Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    //delete user
    public Task<Void> remove(String key)
    {
        return  databaseReference.child(key).removeValue();
    }

}
