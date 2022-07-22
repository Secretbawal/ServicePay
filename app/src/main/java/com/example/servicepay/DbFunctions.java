package com.example.servicepay;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DbFunctions {
    private DatabaseReference databaseReference;
    public DbFunctions(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Records.class.getSimpleName());
    }
    public Task<Void> add(Records rec) {
        return databaseReference.push().setValue(rec);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }
    public Query get(String key){

        if (key == null) {
            return databaseReference.orderByKey().limitToLast(8);
        }
            return databaseReference.orderByKey().startAfter(key).limitToLast(8);
    }

}
